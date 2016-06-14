package examples.optional;


import org.junit.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OptionalTest {
    @Test
    public void whatIsInTheBox() throws Exception {
        IntStream.range(0,10).forEach( i -> {
            Optional<Optionals.Cat> cat = Optionals.whatsInTheBox();
            assertTrue(cat.isPresent() || !cat.isPresent());

            System.out.println(cat.map(c ->
                    String.format("The cat in the box is called '%s'", c.name))
            );
        });
    }

    @Test
    public void gameTest() throws Exception {
        int tries = 0;
        while(true) {
            if(Optionals.doYouWantToPlayAGame().isPresent()) {
              break;
            }
            tries ++;
        }
        System.out.println(String.format("It took %d tries to get 'Have a nice day'!", tries));
    }

    @Test
    public void yesNoMaybe() throws Exception {
        assertThat(Optionals.yesNoMaybe(), allOf(
                is(greaterThanOrEqualTo(0l)),
                is(lessThanOrEqualTo(10l))
        ));
    }
}
