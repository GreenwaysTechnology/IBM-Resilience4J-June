package demo.lazy;

import io.vavr.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
public class LazyTest {

    @Test
    void testLazy1(){
        Lazy<Integer> lazyValue = Lazy.of(() -> new Random().nextInt());
        log.info("has been evaluated: " + lazyValue.isEvaluated());
        Integer result = lazyValue.get();
        log.info("has been evaluated: " + lazyValue.isEvaluated());
        log.info("Result: " + result);
        log.info("Result run again: " + lazyValue.get());
        log.info("Result run again: " + lazyValue.get());
        log.info("Result run again: " + lazyValue.get());
    }
}
