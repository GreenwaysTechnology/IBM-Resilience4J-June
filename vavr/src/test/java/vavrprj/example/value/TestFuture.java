package vavrprj.example.value;


import io.vavr.collection.Stream;
import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;

@Tags({@Tag("either")})
@Slf4j
public class TestFuture {

    @Test
    public void testFuture() throws InterruptedException {
        log.info("Test Future - BEGIN");

        Future<Integer> magicInt = Future.of(Executors.newSingleThreadExecutor(), () -> {
            log.info("We have begun");
            Stream<Integer> take = Stream.from(1).take(500000);
            for (int t : take){
                if (t%1000 == 0)
                    log.info("t is now: " + t);
            }
            return  new Random().nextInt();
        }).onComplete(finalresult -> {
            log.info("YES WE ARE DONE WITH TOUGH WORK");
        });
        Thread.sleep(100);
        magicInt.cancel();
        log.info("is cancelled");
//        log.info("Value: " + magicInt.getValue());

        magicInt.await();
        log.info("is Completed: " + magicInt.isCompleted());
        log.info("Test Future - END");
    }




}
