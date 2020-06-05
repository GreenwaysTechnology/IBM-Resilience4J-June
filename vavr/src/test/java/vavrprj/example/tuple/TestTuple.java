package vavrprj.example.tuple;

import io.vavr.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Slf4j
@Tags({@Tag("tuple")})
public class TestTuple {

    @Test
    void testTuple(){
        Tuple0 empty = Tuple.empty();
        Tuple1<String> t1 = Tuple.of("mystr");
        Tuple4<String, String, Integer, Double> tomatoe = Tuple.of("tomatoe","Spain", 5, 15.5d);
        Tuple4<String, String, Integer, Double> red_tomatoe = tomatoe.update1("Red Tomatoe");
        Tuple4<String, String, Integer, Double> brutto = tomatoe.map((name, country, qty, price) ->
                Tuple.of(
                        name + qty,
                        country.toLowerCase(),
                        qty - 1,
                        price * 1.25
                )
        );

        Integer apply = brutto.apply((name, country, qty, price) -> name.length() + country.length() + qty + price.intValue());

        log.info("tomatoe: " + tomatoe);
        log.info("red_tomatoe: " + red_tomatoe);
        log.info("brutto: " + brutto);
        log.info("one value: " + apply);

    }
}
