package examples.b;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CollectionsTest {
  @Test
  public void countWords() throws Exception {
    assertThat(Collections.countWords(), is(20846L));
  }

  @Test
  public void countDistinctWords() throws Exception {
    assertThat(Collections.distinctWords(), is(5518L));
  }

  @Test
  public void longestDistinctWord() throws Exception {
    assertThat(Collections.longestDistinctWord(), is(31L));
  }

  @Test
  public void averageWordLength() throws Exception {
    assertThat(Collections.averageWordLength(), is(4L));
  }

  @Test
  public void averageDistinctWordLength() throws Exception {
    assertThat(Collections.averageDistinctWordLength(), is(6L));
  }
}