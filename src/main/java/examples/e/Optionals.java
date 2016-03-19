package examples.e;

import support.Sources;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Optionals {

  public class Cat {
    public final String name;
    public Cat(String name) {
      this.name = name;
    }
  }

  public Optional<Cat> whatIsInTheBox() {
    if (new Random().nextInt(1) > 0) {
      return Optional.of(new Cat(Sources.infiniteWordStream().findFirst().get()));
    }
    else return Optional.empty();
  }

  /**
   * If 'day' occurs in the first 10 random words return a greeting
   * but what happens if it's not there?
   *
   * @return
   */
  public static Optional doYouWantToPlayAGame() throws IOException {
    return Sources.infiniteWordStream()
      .limit(10)
      .filter("day"::equals)
      .map(x -> "Have a nice " + x)
      .findFirst();
  }

  public static long yesNoMaybe() {
    return Sources.infiniteOptionalBooleansStream()
      .limit(10)
      .map(x -> x.orElse(false))
      .filter(b -> b)
      .count();
  }
}
