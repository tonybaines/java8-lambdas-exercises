package examples.streams;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;

import static examples.streams.Extension.*;
import static examples.streams.Extension.Books.Dune;
import static examples.streams.Extension.Books.SpotTheDog;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ExtensionTest {
  @Test
  public void bookReadersOnMarchTheFirst() throws Exception {
    assertThat(
      getReadersOfBooks(readers("Tom", "Harry"), books(Dune, SpotTheDog), MARCH_1),
      containsInAnyOrder("Tom", "Harry")
    );
  }

  @Test
  public void bookReadersOnMarchTheSecond() throws Exception {
    assertThat(
      getReadersOfBooks(readers("Tom", "Harry", "Bert", "Harry"), books(Dune, SpotTheDog), MARCH_2),
      containsInAnyOrder("Harry", "Bert")
    );
  }

  @Test
  public void bookReadersOnMarchTheThird() throws Exception {
    assertThat(
      getReadersOfBooks(readers("Bert", "Harry", "Ernie"), books(Dune, SpotTheDog), MARCH_3),
      containsInAnyOrder("Bert", "Ernie")
    );
  }

  private Collection<String> readers(String... readers) {
    return Lists.newArrayList(readers);
  }

  private Collection<Books> books(Books... books) {
    return Lists.newArrayList(books);
  }
}