package support;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class Sources {
  public static Collection<String> words() throws IOException {
    URL resource = Resources.getResource("Sonnets.txt");
    return Lists.newArrayList(Resources.toString(resource, Charsets.UTF_8).split("\\s"));
  }

}
