package examples;

import java.util.Random;
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


  public static void commonProvidedFunctionTypes() {

    Supplier<Integer> diceRollsAIC = new Supplier<Integer>() {
      @Override
      public Integer get() {
        return new Random().nextInt(7)+1;
      }
    };

    Supplier<Integer> diceRollsLambda = () -> new Random().nextInt(7)+1;



    BiFunction<Supplier<Integer>, Supplier<Integer>, Integer> twoDiceRollsAIC = new BiFunction<Supplier<Integer>, Supplier<Integer>, Integer>() {
      @Override
      public Integer apply(Supplier<Integer> die1, Supplier<Integer> die2) {
        return die1.get()+die2.get();
      }
    };

    BiFunction<Supplier<Integer>, Supplier<Integer>, Integer> twoDiceRollsLambda =
      (die1, die2) -> die1.get()+die2.get();

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

    class Player {
      private final Supplier<Integer> twoDice;

      public Player(Supplier<Integer> twoDice) {
        this.twoDice = twoDice;
      }

      public Integer roll() {
        return twoDice.get();
      }
    }


    final Supplier<Integer> integerSupplier = () -> twoDiceRollsLambda.apply(diceRollsLambda, diceRollsLambda);

    final Player player = new Player(integerSupplier);
    IntStream.range(0,10)
      .map( i -> player.roll())
      .forEach(scoreboardAIC);
  }

  public static void main(String[] args) {
    commonProvidedFunctionTypes();
  }
}
