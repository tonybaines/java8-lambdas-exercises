package examples.b;

import support.Sources;

import java.io.IOException;

/**
 * Use streams to replace these loops
 * N.B the original implementations aren't always optimal for these simple tasks,
 * but they stand-in for more complicated scenarios.
 */
public class Collections {
  public static long countWords() throws IOException {
    return Sources.WORDS.stream()
      .count();
  }

  public static long distinctWords() throws IOException {
    return Sources.WORDS.stream()
      .distinct()
      .count();
  }

  public static long longestDistinctWord() throws IOException {
    return Sources.WORDS.stream()
      .distinct()
      .max((w1, w2) -> w1.length() - w2.length())
      .get()
      .length();
  }

  public static long averageWordLength() throws IOException {
    return Math.round(Sources.WORDS.stream()
      .mapToInt(String::length)
      .average()
      .getAsDouble());
  }

  public static long averageDistinctWordLength() throws IOException {
    return Math.round(Sources.WORDS.stream()
      .distinct()
      .mapToInt(String::length)
      .average()
      .getAsDouble());
  }
}
