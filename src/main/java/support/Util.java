package support;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Util {


  public static void sleepFor(long duration, TimeUnit timeUnit) {
    try {
      Thread.sleep(timeUnit.toMillis(duration));
    } catch (InterruptedException ignored) {
    }
  }

  public static void time(Callable c) throws Exception {
    Instant start = Instant.now();
    c.call();
    System.out.println(String.format("%dms", Instant.now().toEpochMilli() - start.toEpochMilli()));
  }
}
