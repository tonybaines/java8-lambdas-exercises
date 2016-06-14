package examples.optional;

import support.Sources;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Optionals {

  public static class Cat {
    public final String name;
    public Cat(String name) {
      this.name = name;
    }
  }

  public static Optional<Cat> whatsInTheBox() {
    if (new Random().nextInt(3) > 0) {
      return new Cat(Sources.infiniteWordStream().???); // One word needed
    }
    else return Optional.empty();
  }

  /**
   * If 'day' occurs in the first 10 random words return a greeting
   * but what happens if it's not there?
   *
   * @return
   */
  public static Optional<String> doYouWantToPlayAGame() throws IOException {
    return Sources.infiniteWordStream()
      .limit(10)
      .filter(???) // Only interested if the word is 'day'
      .map(x -> "Have a nice " + x)
      .???;
  }

  /* Count the number of 'yesses' in the stream
   */
  public static long yesNoMaybe() {
    return Sources.infiniteOptionalBooleansStream()
      .limit(10)
      .filter(b -> ???)
      .count();
  }
}
