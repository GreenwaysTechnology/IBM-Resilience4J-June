package vavrprj.example.collection;

import io.vavr.collection.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags({@Tag("list")})
@Slf4j
public class TestList {

    @Test
    void testCollections(){
        List<Integer> listOfIntegers = List.of(123, 22, 5454, 123);
        Number sum = listOfIntegers.sum();
        log.info("sum: " + sum);
        for (Object s : Stream.of("hej", "test", "sdasdf", 123)){
            log.info("element: " + s);
        }

        Integer sumreduce = Stream
                .from(1)
                .filter(val -> val % 2 == 0)
                .take(10000)
                .reduce((v1, v2) -> v1 + v2);

        CharSeq sdfasdf = CharSeq.of("sdfasdf");
        Queue<String> minkoe = Queue.of("mike", "søren", "ole", "bent");

//        minkoe.flatMap((s) -> s + "sadfasdf");
        PriorityQueue<String> of = PriorityQueue.of("asdfasdf", "asdfsdafd", "sdfsadfsdaf");
//        of.
    }

    @Test
    void testMaps(){
        HashMap<String, String> of = HashMap.of("key1", "val1", "key2", "val2");
        log.info("map: " + of);
    }
}
