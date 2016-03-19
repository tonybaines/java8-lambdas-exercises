package examples.a;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static support.Sources.WORDS;

/**
 * The details of the operations aren't important, they're just there to introduce
 * some variation.
 * <p>
 * The exercise is to factor out the duplication using lambda expressions
 * <p>
 * As an extension, migrate to using Optional instead of returning null.
 */
public class RemoveDuplication {

  public static File operation1(File workingDir) {
    final Supplier<String> wordsStartingWithA = () -> WORDS.stream()
      .filter(s -> s.startsWith("a"))
      .collect(Collectors.joining(" "));

    final Function<String, String> mangleWords =
      interimResult -> interimResult.toUpperCase().replaceAll("[AEIOU]", "*");

    return safeCreate(workingDir, wordsStartingWithA, mangleWords);
  }

  public static File operation2(File workingDir) {
    Supplier<String> interimResult = () -> WORDS.stream()
      .filter(s -> s.contains("th"))
      .map(s -> Hashing.md5().hashString(s, Charsets.UTF_8).asLong())
      .map(Long::toHexString)
      .collect(Collectors.joining(" "));

    return safeCreate(workingDir, interimResult, Function.identity());
  }

  public static File operation3(File workingDir) {
    Supplier<String> interimResult = () -> WORDS.stream()
        .map(s -> new StringBuilder(s).reverse())
        .collect(Collectors.joining(" "));

    return safeCreate(workingDir, interimResult, Function.identity());
  }


  private static File safeCreate(File workingDir, Supplier<String> interimResultSupplier, Function<String, String> transformToFinalResult) {
    try {
      final File tempFile = File.createTempFile("foo", "bar", workingDir);

      String interimResult = interimResultSupplier.get();

      Files.append(transformToFinalResult.apply(interimResult), tempFile, Charsets.UTF_8);

      return tempFile;
    } catch (IOException e) {
      return null;
    }
  }


}
