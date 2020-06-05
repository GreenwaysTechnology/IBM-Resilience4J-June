package demo.match;

import io.vavr.API;
import io.vavr.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;

@Slf4j
public class TestMatch {

    @Test
    void testMatch1(){
        Integer matchThisNumber = 30;

        String stringResult =
                Match(matchThisNumber).of(
                        Case($(10), "The number 10"),
                        Case($(20), "Much higher than 10 (20)"),
                        Case($(), "We ended up in the wildcard case")
                );
        log.info("String result: " + stringResult);
    }

    @Test
    void testMatchStringAndUsePredicates(){
        String matchOnthis = "lemon";

        String result = Match(matchOnthis).of(
                Case($(isIn("truck", "motorbike", "rollerskates")), "We have a match in the vehicles list"),
                Case($(isIn("apple", "orange", "lemon")), "We have a match in the fruit list"),
                Case($(), "We got no match...")
        );

        log.info("Result: " + result);

    }
}
