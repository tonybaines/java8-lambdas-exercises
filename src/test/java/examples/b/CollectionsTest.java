package examples.b;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CollectionsTest {
  @Test
  public void countWords() throws Exception {
    assertThat(Collections.countWords(), is(1410671L));
  }

  @Test
  public void countDistinctWords() throws Exception {
    assertThat(Collections.distinctWords(), is(67781L));
  }

  @Test
  public void longestDistinctWord() throws Exception {
    assertThat(Collections.longestDistinctWord(), is(40L));
  }

  @Test
  public void averageWordLength() throws Exception {
    assertThat(Collections.averageWordLength(), is(2L));
  }

  @Test
  public void averageDistinctWordLength() throws Exception {
    assertThat(Collections.averageDistinctWordLength(), is(7L));
  }
}