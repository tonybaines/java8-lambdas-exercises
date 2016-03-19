package examples.a;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static support.Sources.WORDS;

/**
 * The details of the operations aren't important, they're just there to introduce
 * some variation.
 *
 * The exercise is to factor out the duplication using lambda expressions
 *
 * As an extension, migrate to using Optional instead of returning null.
 */
public class RemoveDuplication {

  public static File operation1(File workingDir) {
    try {
      final File tempFile = File.createTempFile("foo", "bar", workingDir);

      String interimResult = WORDS.stream()
        .filter(s -> s.startsWith("a"))
        .collect(Collectors.joining(" "));

      Files.append(interimResult.toUpperCase().replaceAll("[AEIOU]", "*"), tempFile, Charsets.UTF_8);

      return tempFile;
    }
    catch (IOException e) {
      return null;
    }
  }

  public static File operation2(File workingDir) {
    try {
      final File tempFile = File.createTempFile("foo", "bar2", workingDir);

      String interimResult = WORDS.stream()
        .filter(s -> s.contains("th"))
        .map(s -> Hashing.md5().hashString(s, Charsets.UTF_8).asLong())
        .map(Long::toHexString)
        .collect(Collectors.joining(" "));

      Files.append(interimResult, tempFile, Charsets.UTF_8);

      return tempFile;
    }
    catch (IOException e) {
      return null;
    }
  }



  public static File operation3(File workingDir) {
    try {
      final File tempFile = File.createTempFile("foo", "bar3", workingDir);

      String interimResult = WORDS.stream()
        .map(s -> new StringBuilder(s).reverse())
        .collect(Collectors.joining(" "));

      Files.append(interimResult, tempFile, Charsets.UTF_8);

      return tempFile;
    }
    catch (IOException e) {
      return null;
    }
  }


}
