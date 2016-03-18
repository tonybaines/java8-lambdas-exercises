package support;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Sources {
  public static List<String> fewWords() throws IOException {
    URL resource = Resources.getResource("Sonnets.txt");
    return Lists.newArrayList(Resources.toString(resource, Charsets.UTF_8).split("\\s"));
  }
  public static Collection<String> moreWords() throws IOException {
    URL resource = Resources.getResource("Plays.txt");
    return Lists.newArrayList(Resources.toString(resource, Charsets.UTF_8).split("\\s"));
  }

  public static Stream<String> infiniteWordStream() throws IOException {
    List<String> words = fewWords();
    return new Random().ints(0, words.size())
      .mapToObj(words::get);
  }

  public static Collection<String> words() throws IOException {
    return moreWords();
  }

}
