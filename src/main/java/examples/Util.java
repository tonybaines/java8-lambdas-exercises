package examples;

import java.util.concurrent.TimeUnit;

public class Util {


  public static void safeSleep(long duration, TimeUnit timeUnit) {
    try {
      Thread.sleep(timeUnit.toMillis(duration));
    } catch (InterruptedException ignored) { }
  }
}
