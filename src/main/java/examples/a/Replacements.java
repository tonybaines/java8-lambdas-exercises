package examples.a;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.code.tempusfugit.concurrency.ThreadUtils.sleep;
import static com.google.code.tempusfugit.temporal.Duration.seconds;

public class Replacements {

  /**
   * The Runnable is directly replaceable with an anonymous lambda
   */
  public static AtomicBoolean thread() {
    final AtomicBoolean running = new AtomicBoolean(true);
    final CountDownLatch counter = new CountDownLatch(2);
    Thread thread = new Thread(() -> {
      while (counter.getCount() > 0) {
        System.out.println(Instant.now().toString());
        sleep(seconds(2));
        counter.countDown();
      }
      running.set(false);
    });

    thread.start();
    return running;
  }

  /**
   * The Comparator is directly replaceable with an anonymous lambda
   */
  public static List<Integer> comparator(List<Integer> src) {
    List<Integer> result = new ArrayList<>(src);

    result.sort((o1, o2) -> (o1 % 2) - 1);
    return result;
  }


  /**
   * The interface implementation is directly replaceable with an anonymous lambda
   */
  public static int customInterface() {
    AtomicInteger evensCount = new AtomicInteger(0);
    AtomicInteger oddsCount = new AtomicInteger(0);

    withIntegers(1, 20, i -> {
      if (i % 2 == 0) {
        evensCount.incrementAndGet();
      } else {
        oddsCount.incrementAndGet();
      }
    });

    return evensCount.get();
  }

  interface StuffDoer {
    void doStuffWith(Integer i);
  }

  private static void withIntegers(int from, int to, StuffDoer doer) {
    for (int i = from; i <= to; i++) {
      doer.doStuffWith(i);
    }
  }

}
