package demo.tuple;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TupleTest {

    @Test
    void testTuple1() {
        Tuple3<String, Integer, String> tuple3 = Tuple.of("mike", 41, "basketball");
        log.info("Tuple: " + tuple3);
        Tuple3<String, Integer, String> mappedtuple = tuple3.map(s -> s.toUpperCase(), i -> i - 5, s -> s.substring(2));
        log.info("Tuple Mapped: " + mappedtuple);

    }
}
