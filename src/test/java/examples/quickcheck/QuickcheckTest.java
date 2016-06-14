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

  // 1. For all Strings, their length is greater than or equal zero


  // 2. Any two Strings, when concatenated, have a length equal
  // to the sum of their individual lengths


  // 3. One integer divided by another can never be zero
  // (N.B. need to constrain ranges)


  // 4. Think of a before / after property of the list
  @Property public void customComparator(List<Integer> list) {
    final List<Integer> sorted = Replacements.comparator(list);
    // ????
  }


}
