package javagruppen;

import io.vavr.*;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TupleTest {

    @Test
    void testTuple() {
        Tuple4<String, Integer, Integer, Integer> dataklump = Tuple.of("Mike", 100, 192, 41);
        log.info(dataklump.toString());
    }

    @Test
    void testFunction() {
        Function3<String, Integer, String, String> f3 = (s1, i1, s2) -> s1 + i1 + s2;
        String resultat = f3.apply("Hej ", 10, "Javagruppen");
        Function2<Integer, String, String> godaften = f3.apply("Godaften");
        log.info(resultat);
        log.info(godaften.apply(20, "Verden"));
    }


    @Test
    void testCurried() {
        Function3<String, Integer, String, String> f3 = (s1, i1, s2) -> s1 + i1 + s2;
        log.info(f3
                .curried()
                .apply("var1")
                .apply(20)
                .apply("test"));
    }


    @Test
    void testLifted(){
        Function2<Integer, Integer, Integer> divider = (i1,i2) -> {
            return i1/i2;
        };
        Function2<Integer, Integer, Option<Integer>> lift = Function2.lift(divider);
        Option<Integer> apply = lift.apply(10, 0);
        log.info(apply.toString());
        log.info("Værdien blev:" + apply.getOrElse(-1));

        Function1<Option<Integer>, String> f1 = (i) -> "Resultatet blev: " + i.getOrElse(-200);
        Function2<Integer, Integer, String> fstring = lift.andThen(f1);
        log.info("Fstring result " + fstring.apply(20,0));
    }
}
