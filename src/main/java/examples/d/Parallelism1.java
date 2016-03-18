package examples.d;

import support.Sources;

import java.math.BigInteger;

import static com.google.common.math.LongMath.factorial;
import static support.Util.time;


public class Parallelism1 {

  public static void main(String[] args) {

    /*
      Which is faster?
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

