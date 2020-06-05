package demo.function;

import io.vavr.collection.Stream;
import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FutureTest {


    @Test
    void futureTest() throws InterruptedException {
        Future<Integer> future = Future.of(() -> {
            for (Integer i : Stream.from(10).take(5000000)) {
                if (i % 1000 == 0) {
                    System.out.println("i er nu " + i);
                }
            }
            return 42;
        }).onComplete((i) -> log.info("Nu er vi færdige Jæææ" + i) );

//
        log.info("Vent");
        Thread.sleep(1000);
        log.info("Cancel");
        future.cancel();
        log.info("Sleep lidt mere");
        Thread.sleep(10000);
        log.info("Efter vent");


    }

}
