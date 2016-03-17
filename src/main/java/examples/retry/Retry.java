package examples.retry;

import javaslang.control.Try;
import javaslang.control.Try.CheckedSupplier;

import java.util.concurrent.TimeUnit;

import static examples.Util.safeSleep;

public final class Retry<T> {
  private final int maxTimes;
  private int duration = 0;
  private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

  private Retry(int maxTimes) {
    this.maxTimes = maxTimes;
  }

  public static Retry times(int maxTimes) {
    return new Retry(maxTimes);
  }

  public Retry<T> pausing(int duration, TimeUnit timeUnit) {
    this.duration = duration;
    this.timeUnit = timeUnit;
    return this;
  }

  public Try<T> operation(CheckedSupplier<T> operation) {
    int tries = 1;
    while (true) {
      try {
        T result = operation.get();
        return Try.success(result);
      } catch (Throwable e) {
        if (tries < maxTimes) {
          tries++;
          safeSleep(duration, timeUnit);
          continue;
        } else {
          return Try.failure(e);
        }
      }
    }
  }

}
