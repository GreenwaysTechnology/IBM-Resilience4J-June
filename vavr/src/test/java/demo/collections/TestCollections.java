package demo.collections;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestCollections {

    @Test
    void testList(){
        List<Integer> mylist = List.of(1, 2, 3, 100);
        List<Integer> newList = mylist.append(200);
        for (Integer myiterator : newList) {
            log.info("value: " + myiterator);
        }
    }


    @Test
    void testStream(){
        Stream<Integer> take = Stream.from(5)
                .filter((n) -> n % 2 == 0)
                .take(1000);

        java.util.List<Integer> javalist = take.toJavaList();

     /*   for (Integer takevalue : take){
            log.info("Value: " + takevalue);
        }
*/
        Number sum = take.sum();
        log.info("Sum: " + sum);

        Integer reduce = take.reduce((i1, i2) -> i1 + i2);
        log.info("Sum by reduce: " + reduce);


    }

    @Test
    void testMaps(){
        HashMap<String, Integer> map = HashMap.of("key1", 10, "key2_twenty", 20, "key3", -1000);
        log.info("Map: " + map);

        Tuple2<String, String> t1 = Tuple.of("key1", "value1");
        Tuple2<String, String> t2 = Tuple.of("key2", "value2");
        Tuple2<String, String> t3 = Tuple.of("key3", "value3");
        HashMap<String, String> mapFromTuples = HashMap.of(t1).put(t2).put(t3);
        log.info("Map from tuples " + mapFromTuples);

        java.util.HashMap<String, String> stringStringHashMap = mapFromTuples.toJavaMap();
    }

}
