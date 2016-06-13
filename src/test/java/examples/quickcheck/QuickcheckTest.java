package examples.quickcheck;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import examples.lambdas.Replacements;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

@org.junit.runner.RunWith(JUnitQuickcheck.class)
public class QuickcheckTest {

  @Property public void checkStringLength(String s) {
    final int actual = s.length();
    System.out.println(String.format("%s.length() %d", s, actual));
    assertThat(actual, is(greaterThanOrEqualTo(0)));
  }

  @Property public void concatenationLength(String s1, String s2) {
    assertThat("Lengths of the component and joined strings are different!", s1.length() + s2.length(), is((s1 + s2) .length()));
  }


  // Annotations can restrict built-in generators
  @Property public void integerDivision(int x, @InRange(minInt = 1) int y) {
    // Needs an additional precondition because of integer truncation
    if (x >= y) {
      final int actual = x / y;
      System.out.println(String.format("%d / %d = %d", x, y, actual));
      assertThat(actual, is(not(0)));
    }
  }


  @Property public void customComparator(List<Integer> list) {
    final List<Integer> sorted = Replacements.comparator(list);
    assertThat("Different sizes!", sorted.size(), is(list.size()));
    assertThat("Non-deterministic!", sorted, is(Replacements.comparator(list)));
  }


}
