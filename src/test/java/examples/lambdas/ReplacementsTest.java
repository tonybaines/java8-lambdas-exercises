package examples.lambdas;

import com.google.code.tempusfugit.temporal.Timeout;
import com.google.common.collect.Lists;
import examples.extra.Replacements;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReplacementsTest {

  @Test
  public void running() throws Exception {
    AtomicBoolean running = Replacements.thread();
    waitOrTimeout(
      () -> !running.get(),
      Timeout.timeout(seconds(10)));
  }

  @Test
  public void comparator() throws Exception {
    List<Integer> result = Replacements.comparator(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));

    assertThat(result, is(Lists.newArrayList(12, 10, 8, 6, 4, 2, 1, 3, 5, 7, 9, 11, 13)));
  }

  @Test
  public void customInterface() throws Exception {
    assertThat(Replacements.customInterface(), is(10));
  }

}