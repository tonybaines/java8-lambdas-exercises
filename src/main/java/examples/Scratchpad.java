package examples;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.IntStream;

public class Scratchpad {

  public void basics() {
    // Anonymous inner class
    final Function<String, Integer> functionFromStringToInteger = new Function<String, Integer>() {
      @Override
      public Integer apply(String s) {
        return s.length();
      }
    };

    // Rewrite as a lambda (verbose)
    final Function<String, Integer> verboseLambdaStringToInteger = (String s) -> {
        return s.length();
    };

    // Rewrite as lambda (concise syntax, inferred types)
    final Function<String, Integer> inferredTypesLambdaStringToInteger = (s) -> s.length();

    // Rewrite as lambda (method reference)
    final Function<String, Integer> methodReferenceLambdaStringToInteger = String::length;
  }

  /*
  @startuml
  (*)->[String]"Consumer<String>"
  @enduml
  @startuml
  (*)->[String]"Function<String, Integer>"
  "Function<String, Integer>" -> [Integer](*)
  @enduml
  @startuml
  (*)->[(String, String)]"BiFunction<String, String, Integer>"
  "BiFunction<String, String, Integer>" -> [Integer](*)
  @enduml
  @startuml
  "Supplier<Integer>" -> [Integer](*)
  @enduml

  @startuml
  "Supplier<Integer>" --> [Integer]"Function<Integer, String>"
  "Function<Integer, String>" -->[String]"Function<String, Boolean>"
  "Function<String, Boolean>"-->[Boolean]"Consumer<Boolean>"
  @enduml

  @startuml
  "die1:\nIntSupplier" --> [Integer]"Two Dice:\nBiFunction<IntSupplier, IntSupplier, IntSupplier>"
  "die2:\nIntSupplier" --> [Integer]"Two Dice:\nBiFunction<IntSupplier, IntSupplier, IntSupplier>"
  "Two Dice:\nBiFunction<IntSupplier, IntSupplier, IntSupplier>" -->[Integer] "Scoreboard:\nIntConsumer"
  @enduml
   */

  public static void commonProvidedFunctionTypes() {

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

  public static void main(String[] args) {
    commonProvidedFunctionTypes();
  }
}
