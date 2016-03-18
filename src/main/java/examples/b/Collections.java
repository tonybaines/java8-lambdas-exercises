package examples.b;

import support.Sources;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Use streams to replace these loops
 * N.B the original implementations aren't always optimal for these simple tasks,
 * but they stand-in for more complicated scenarios.
 */
public class Collections {
  public static long countWords() throws IOException {
    long count = 0;
    for (String word : Sources.words()) {
      count++;
    }
    return count;
  }

  public static long distinctWords() throws IOException {
    Set distinctWords = new HashSet<>();
    for (String word : Sources.words()) {
      distinctWords.add(word);
    }
    return distinctWords.size();
  }

  public static long longestDistinctWord() throws IOException {
    Set<String> distinctWords = new HashSet<>();
    for (String word : Sources.words()) {
      distinctWords.add(word);
    }

    long maxLength = 0;
    for (String word : distinctWords) {
      maxLength = (word.length() > maxLength) ? word.length() : maxLength;
    }

    return maxLength;
  }

  public static long averageWordLength() throws IOException {
    long count = 0;
    long totalLength = 0;
    for (String word : Sources.words()) {
      count++;
      totalLength = totalLength + word.length();
    }
    return Math.round(totalLength / count);
  }

  public static long averageDistinctWordLength() throws IOException {
    Set<String> distinctWords = new HashSet<>();
    for (String word : Sources.words()) {
      distinctWords.add(word);
    }

    long count = 0;
    long totalLength = 0;
    for (String word : distinctWords) {
      count++;
      totalLength += word.length();
    }
    return Math.round(totalLength / count);
  }
}
