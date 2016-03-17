package examples.replacewithlambda;

import com.google.code.tempusfugit.temporal.Timeout;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;

public class ReplacementsTest {

  @Test
  public void running() throws Exception {
    AtomicBoolean running = Replacements.threads();
    waitOrTimeout(
      () -> !running.get(),
      Timeout.timeout(seconds(10)));
  }

}