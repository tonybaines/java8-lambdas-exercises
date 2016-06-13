package examples.lambdas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class CommonProvidedFunctionTypes {

    public static void main(String[] args) {
        IntSupplier diceRollsAIC = new IntSupplier() {
            // implement the interface method to return a random number between 1 and 6
        };

        // A function returning a function!
        BiFunction<IntSupplier, IntSupplier, IntSupplier> twoDiceRollsAIC = new BiFunction<IntSupplier, IntSupplier, IntSupplier>() {
            // implement the interface method to return a *function* that combines the output of two other functions
        };

        // Consumers must have side-effects to do anything useful
        // (not pure functions)
        IntConsumer scoreboardAIC = new IntConsumer() {
            // implement the interface method to keep and print a running total
        };




        IntSupplier diceRollsLambda; // convert diceRollsAIC to a lambda expression

        BiFunction<IntSupplier, IntSupplier, IntSupplier> twoDiceRollsLambda; // convert twoDiceRollsAIC to a lambda expression

        // A class that uses the 'dice'
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
        final Player player = new Player(???);

        // Access to an external variable in a lambda needs a final reference (as for AIC)
        final AtomicInteger totalScore = new AtomicInteger(0);
        IntStream.range(0,20)
                .map( i -> player.roll())
                .forEach(score -> {
                    // print the running total here
                });

    /*
      Extension
      - roll three, four dice (use the existing functions)
      - Replace the Player class with a function
        - first built-in function interfaces
        - then create your own
     */
    }
}
