package examples.streams;

import support.Sources;

import static support.Util.time;


public class Parallelism {

  public static void main(String[] args) {

    /*
      Which is faster?
      Play with the numbers and the operations
     */
    final int limit = 100000000; // Change the magnitude depending on execution times
    try {
      System.out.println("With parallelism");
      time(() -> Sources.infiniteWordStream()
        .parallel()
        .limit(limit)
        .map(String::toUpperCase)
        .mapToInt(String::length)
//        .mapToLong(i -> factorial(i) + 1)
//        .filter(i -> BigInteger.valueOf(i).isProbablePrime(100))
        .sum());

      System.out.println("Without parallelism");
      time(() -> Sources.infiniteWordStream()
//        .parallel()
        .limit(limit)
        .map(String::toUpperCase)
        .mapToInt(String::length)
//        .mapToLong(i -> factorial(i) + 1)
//        .filter(i -> BigInteger.valueOf(i).isProbablePrime(100))
        .sum());


    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}

