package examples.streams;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static examples.streams.Extension.Books.*;
import static support.Util.list;

/**
 * This example taken from Martin Fowler's blog
 * http://martinfowler.com/articles/refactoring-pipelines.html
 */
public class Extension {
  public static final LocalDate MARCH_1 = LocalDate.of(2015, 03, 01);
  public static final LocalDate MARCH_2 = LocalDate.of(2015, 03, 02);
  public static final LocalDate MARCH_3 = LocalDate.of(2015, 03, 03);

  /*
   TODO: Refactor this to use streams
   */
  public static Set<String> getReadersOfBooks(Collection<String> readers, Collection<Books> books, LocalDate date) {
    DataService dataService = new DataService() { };

    Map<String, Collection<Books>> data = dataService.getBooksReadOn(date);

    return data.entrySet().stream()
      .filter(e -> readers.contains(e.getKey()))
      .filter(e -> hasSharedMembers(books, e.getValue()))
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
  }

  /**
   * Useful utility method for the refactoring
   * @param a
   * @param b
   * @return true if there are any shared members between the two collections
   */
  private static boolean hasSharedMembers(Collection a, Collection b) {
    return a.stream().anyMatch( x -> b.contains(x));
  }


  public enum Books {SpotTheDog, Ulysses, Dune, LordOfTheFlies}

  private interface DataService {
    default Map<String, Collection<Books>> getBooksReadOn(LocalDate date) {
      final Map<String, Collection<Books>> books = new HashMap<>();
      if (date.equals(MARCH_1)) {
        books.put("Tom", list(SpotTheDog));
        books.put("Harry", list(Dune));
        books.put("Eric", list(SpotTheDog, Ulysses));
      } else if (date.equals(MARCH_2)) {
        books.put("Harry", list(Dune));
        books.put("Eric", list(Ulysses));
        books.put("Ernie", list(SpotTheDog));
        books.put("Bert", list(Ulysses, Dune));
      } else if (date.equals(MARCH_3)) {
        books.put("Ernie", list(SpotTheDog, LordOfTheFlies));
        books.put("Bert", list(Dune));
      }
      return books;
    }
  }
}
