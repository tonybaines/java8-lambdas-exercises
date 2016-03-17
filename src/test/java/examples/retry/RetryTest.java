package examples.retry;

import javaslang.control.Try;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RetryTest {
  @Test
  public void noNeedToRetrySomethingWhichWorks() throws Exception {
    final AtomicInteger tries = new AtomicInteger(0);
    Try<String> result = Retry
      .times(3).pausing(1, TimeUnit.SECONDS)
      .operation(() -> {
        tries.incrementAndGet();
        return "hello";
      });

    assertTrue(result.isSuccess());
    assertThat(tries.get(), is(1));
  }

  @Test
  public void anOperationWhichFailsWithAnExceptionEveryTimeWillBeRetried() throws Exception {
    final AtomicInteger tries = new AtomicInteger(0);
    Try<String> result = Retry
      .times(3).pausing(1, TimeUnit.SECONDS)
      .operation(() -> {
        tries.incrementAndGet();
        throw new RuntimeException("FAILED");
      });

    assertFalse(result.isSuccess());
    assertThat(tries.get(), is(3));
  }

  @Test
  public void anOperationWhichFailsThenSuceeedsWithinTheRetryLimitReturnsAValue() throws Exception {
    final AtomicInteger tries = new AtomicInteger(0);
    Try<String> result = Retry
      .times(3).pausing(1, TimeUnit.SECONDS)
      .operation(() -> {
        int i = tries.incrementAndGet();
        if (i <2){
          throw new RuntimeException("FAILED");
        }
        else {
          return "hello";
        }
      });

    assertTrue(result.isSuccess());
    assertThat(tries.get(), is(2));
  }
}