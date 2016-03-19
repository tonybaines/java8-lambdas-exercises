package examples.c;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;

import static com.google.common.io.Files.readFirstLine;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

public class RemoveDuplicationTest {

  @Test
  public void operation1Test() throws Exception {
    final File file = RemoveDuplication.operation1(Files.createTempDir());
    assertThat(readFirstLine(file, Charsets.UTF_8), startsWith("*NY*N* *NYWH*R*"));
  }

  @Test
  public void operation2Test() throws Exception {
    final File file = RemoveDuplication.operation2(Files.createTempDir());
    assertThat(readFirstLine(file, Charsets.UTF_8), startsWith("db6699df6d2cc48f"));
  }

  @Test
  public void operation3Test() throws Exception {
    final File file = RemoveDuplication.operation3(Files.createTempDir());
    assertThat(readFirstLine(file, Charsets.UTF_8), startsWith("ehT"));
  }

  @Test
  public void failsWhenTheWorkingDirectoryDoesNotExist() throws Exception {
    assertThat(RemoveDuplication.operation1(new File("does-not-exist")), is(nullValue()));
  }
}