package demo.future;

import io.vavr.collection.Stream;
import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

@Slf4j
public class FutureTest {


    @Test
    void testFuture1() throws InterruptedException {
        log.info("TestFuture1 - BEGIN");

        Future<Integer> magicNumber = Future.of(Executors.newSingleThreadExecutor(), () -> {
            for (Integer i : Stream.from(1).take(500000)){
                if (i%1000 == 0)
                    log.info("We are at number: " + i);
            }
            return 42;
        }).onComplete((i) -> log.info("Complete Callback has been reached. The result was calculated to be: " + i));

        magicNumber.await();

        log.info("TestFuture1 - END");
    }
}
