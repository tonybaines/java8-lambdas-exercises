package examples.replacewithlambda.b;

import support.Sources;

import java.io.IOException;

/**
 * Use streams to replace these loops
 * N.B the original implementations aren't always optimal for these simple tasks,
 * but they stand-in for more complicated scenarios.
 */
public class Collections {
  public static long countWords() throws IOException {
    return Sources.words().stream()
      .count();
  }

  public static long distinctWords() throws IOException {
    return Sources.words().stream()
      .distinct()
      .count();
  }

  public static long longestDistinctWord() throws IOException {
    return Sources.words().stream()
      .distinct()
      .max((w1, w2) -> w1.length() - w2.length())
      .get()
      .length();
  }

  public static long averageWordLength() throws IOException {
    return Math.round(Sources.words().stream()
      .mapToInt(String::length)
      .average()
      .getAsDouble());
  }

  public static long averageDistinctWordLength() throws IOException {
    return Math.round(Sources.words().stream()
      .distinct()
      .mapToInt(String::length)
      .average()
      .getAsDouble());
  }
}
