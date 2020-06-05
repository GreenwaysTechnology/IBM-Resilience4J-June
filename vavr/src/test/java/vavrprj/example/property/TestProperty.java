package vavrprj.example.property;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestProperty {

    @Test
    void testProperty(){
        Arbitrary<Integer> ints = Arbitrary.integer();
        log.info("ints: " + ints);
// square(int) >= 0: OK, passed 1000 tests.
        Property.def("square(int) test")
                .forAll(ints)
                .suchThat(i -> i * i >= 0 && i < 1000)
                .check(100,200)
                .assertIsSatisfied();

        Property.def("my test")
                .forAll(Arbitrary.of("fggfjf"))
                .suchThat(s -> s.length() < 50)
                .check()
                .assertIsSatisfied();
    }
}
