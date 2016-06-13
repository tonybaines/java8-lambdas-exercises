package examples.lambdas;

/*============================================================================*
 * - COPYRIGHT NOTICE -
 *
 * Copyright (c) British Telecommunications plc, 2010, All Rights Reserved
 *
 * The information contained herein and which follows is proprietary
 * information and is the property of BT. This information is not to be
 * divulged, released, copied, reproduced, or conveyed to unauthorised
 * personnel,companies or other institutions without the direct and expressed
 * approval in writing of BT
 *
 *============================================================================*/

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class CommonProvidedFunctionTypes {

    public static void main(String[] args) {
        IntSupplier diceRollsAIC = new IntSupplier() {
            @Override
            public int getAsInt() {
                return new Random().nextInt(6)+1;
            }
        };

        // A function returning a function!
        BiFunction<IntSupplier, IntSupplier, IntSupplier> twoDiceRollsAIC = new BiFunction<IntSupplier, IntSupplier, IntSupplier>() {
            @Override
            public IntSupplier apply(IntSupplier die1, IntSupplier die2) {
                return new IntSupplier() {
                    @Override
                    public int getAsInt() {
                        return die1.getAsInt()+die2.getAsInt();
                    }
                };
            }
        };

        // Consumers must have side-effects to do anything useful
        // (not pure functions)
        IntConsumer scoreboardAIC = new IntConsumer() {
            private int totalScore = 0;

            @Override
            public void accept(int score) {
                totalScore += score;
                System.out.println(String.format("Rolled %d. Total now %d", score, totalScore));
            }
        };




        IntSupplier diceRollsLambda = () -> new Random().nextInt(6)+1;

        BiFunction<IntSupplier, IntSupplier, IntSupplier> twoDiceRollsLambda =
                (die1, die2) -> ( () -> die1.getAsInt()+die2.getAsInt() );

        // A class that uses
        class Player {
            private final IntSupplier dice;
            Player(IntSupplier dice) {
                this.dice = dice;
            }
            Integer roll() {
                return dice.getAsInt();
            }
        }

        // Compose the pieces together
        final Player player = new Player(twoDiceRollsLambda.apply(diceRollsLambda, diceRollsLambda));

        // Access to an external variable in a lambda needs a final reference (as for AIC)
        final AtomicInteger totalScore = new AtomicInteger(0);
        IntStream.range(0,20)
                .map( i -> player.roll())
                .forEach(score -> {
                    // An IntConsumer, in lambda notation
                    // could have used scoreboardAIC here instead
                    System.out.println(String.format("Rolled %d. Total now %d", score, totalScore.addAndGet(score)));
                });

    /*
      Extension
      - roll three, four dice (use the existing functions)
      - Replace the Player class with a function
        - first built-in function interfaces
        - then create your own
     */

    /*
      Discussions
      - Lego brick assembly of functionality
      - Relative benefits of classes/functions, anonymous/named
     */
    }
}
