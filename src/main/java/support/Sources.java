package support;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import static support.Util.list;

public class Sources {

  public static final List<String> WORDS = wordsFrom("Plays.txt");


  public static Stream<String> infiniteWordStream() {
    return new Random().ints(0, WORDS.size())
      .mapToObj(WORDS::get);
  }

  private static List<String> wordsFrom(String resourceName) {
    URL resource = Resources.getResource(resourceName);
    try {
      return list(Resources.toString(resource, Charsets.UTF_8).split("\\s"));
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public static Stream<Optional<Boolean>> infiniteOptionalBooleansStream() {
    return new Random().ints(0, 2).mapToObj(i -> {
        switch (i) {
          case 0:
            return Optional.of(true);
          case 1:
            return Optional.of(false);
          default:
            return Optional.empty();
        }
      }
    );
  }

}
