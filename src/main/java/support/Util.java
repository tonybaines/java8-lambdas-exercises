package support;

import com.google.common.collect.Lists;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Util {

  public static <T> List<T> list(T... items) {
    return Lists.newArrayList(items);
  }

  public static void sleepFor(long duration, TimeUnit timeUnit) {
    try {
      Thread.sleep(timeUnit.toMillis(duration));
    } catch (InterruptedException ignored) {
    }
  }

  public static int between(int min, int max) {
    return new Random().nextInt(max) + min;
  }

  public static void time(Callable c) throws Exception {
    Instant start = Instant.now();
    c.call();
    System.out.println(String.format("%dms", Instant.now().toEpochMilli() - start.toEpochMilli()));
  }

  public static void log(String message, Object... args) {
    System.out.println(Instant.now().toString() + "\t" + String.format(message, args));
  }
}
