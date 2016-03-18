package examples.d;

import support.Sources;

import static support.Util.time;


public class Parallelism1 {

  public static void main(String[] args) {

    /*
      Which is faster?
     */
    try {
      System.out.println("With parallelism");
      time(() -> Sources.infiniteWordStream()
        .parallel()
        .limit(100000000)
        .mapToInt(String::length)
        .count());

      System.out.println("Without parallelism");
      time(() -> Sources.infiniteWordStream()
//        .parallel()
        .limit(100000000)
        .mapToInt(String::length)
        .count());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}

