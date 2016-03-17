package examples.replacewithlambda;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.code.tempusfugit.concurrency.ThreadUtils.sleep;
import static com.google.code.tempusfugit.temporal.Duration.seconds;

public class Replacements {

  public static AtomicBoolean threads() {
    final AtomicBoolean running = new AtomicBoolean(true);
    final CountDownLatch counter = new CountDownLatch(3);
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (counter.getCount() > 0) {
          System.out.println(Instant.now().toString());
          sleep(seconds(2));
          counter.countDown();
        }
        running.set(false);
      }
    });

    thread.start();
    return running;
  }
}
