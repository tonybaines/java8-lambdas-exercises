package examples.streams;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CollectionsTest {
  @Test
  public void countWords() throws Exception {
    assertThat(Collections.countWords(), is(903976L));
  }

  @Test
  public void countDistinctWords() throws Exception {
    assertThat(Collections.distinctWords(), is(42527L));
  }

  @Test
  public void longestDistinctWord() throws Exception {
    assertThat(Collections.longestDistinctWord(), is(37L));
  }

  @Test
  public void averageWordLength() throws Exception {
    assertThat(Collections.averageWordLength(), is(4L));
  }

  @Test
  public void averageDistinctWordLength() throws Exception {
    assertThat(Collections.averageDistinctWordLength(), is(7L));
  }
}