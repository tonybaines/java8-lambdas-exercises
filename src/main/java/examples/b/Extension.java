package examples.b;

import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.util.*;

import static examples.b.Extension.Books.*;

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
    DataService dataService = new DataService(){ };

    Set<String> result = new HashSet<>();
    Map<String, Collection<Books>> data = dataService.getBooksReadOn(date);
    for (Map.Entry<String, Collection<Books>> e : data.entrySet()) {
      for (Books book : books) {
        if (e.getValue().contains(book) && readers.contains(e.getKey())) {
          result.add(e.getKey());
        }
      }
    }
    return result;
  }




  public enum Books {SpotTheDog, Ulysses, Dune, LordOfTheFlies}

  private interface DataService{
    default Map<String,Collection<Books>> getBooksReadOn(LocalDate date) {
      final Map<String, Collection<Books>> books = new HashMap<>();
      if (date.equals(MARCH_1)) {
        books.put("Tom", Lists.newArrayList(SpotTheDog));
        books.put("Harry", Lists.newArrayList(Dune));
        books.put("Eric", Lists.newArrayList(SpotTheDog, Ulysses));
      }
      else if (date.equals(MARCH_2)) {
        books.put("Harry", Lists.newArrayList(Dune));
        books.put("Eric", Lists.newArrayList(Ulysses));
        books.put("Ernie", Lists.newArrayList(SpotTheDog));
        books.put("Bert", Lists.newArrayList(Ulysses, Dune));
      }
      else if (date.equals(MARCH_3)) {
        books.put("Ernie", Lists.newArrayList(SpotTheDog, LordOfTheFlies));
        books.put("Bert", Lists.newArrayList(Dune));
      }
      return books;
    }
  }
}
