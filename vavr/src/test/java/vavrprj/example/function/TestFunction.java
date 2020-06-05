package vavrprj.example.function;

import io.vavr.*;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Tags({@Tag("function")})
@Slf4j
public class TestFunction {

    @Test
    void testFunction(){
        Function4<Integer, Integer, Integer, Integer,Integer> f4 = (v1,v2,v3,v4) -> v1+v2+v3+v4;
        Integer sum = f4.apply(1, 2, 3, 4);
        log.info("Sum: " + sum);
        Function2<Integer, Integer, Integer> f2 = f4.apply(10,20);
        Integer sum2 = f2.apply(1, 2);
        log.info("Sum2: " + sum2);
    }

    @Test
    void testComposition(){
        Function2<String, Integer, String> f2 = (s1, i1) -> s1.toUpperCase() + i1;
        Function1<String, String> ftolower= (s1) -> s1.toLowerCase();
        Function1<String, String> faddtext= (s1) -> s1.toLowerCase() + "JajaJajaja";
        Function2<String, Integer, String> compositeFunction = f2.andThen(ftolower);

        Function1<String, String> compose = faddtext.compose(ftolower);
        String composeResult = compose.apply("My Text");
        log.info("result compose: " + composeResult);

        String result = compositeFunction.apply("HeJBalfladsflda", 12);
        log.info("result: " + result);
    }

    @Test
    void testLifting(){
        Function2<Integer, Integer, Integer> divide = (i1, i2) -> i1/i2;
        Function2<Integer, Integer, Option<Integer>> lift = Function2.lift(divide);
//        Integer divzero = divide.apply(1, 0);
        Option<Integer> apply = lift.apply(10, 2);
        log.info("DivZero Result [" + apply + "]");
    }

    @Test
    void testCurried(){
        Function3<Integer, Integer, Integer, Integer> f3 = (i1, i2, i3)-> i1+i2+i3;
        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> curried = f3.curried();
        Function1<Integer, Function1<Integer, Integer>> a10 = f3.curried().apply(10);
        Integer result = a10.apply(20).apply(30);


    }



    @Test
    void testMemorized(){
        Function0<Integer> randnum = () -> new Random().nextInt();
        Function0<Integer> memoized = randnum.memoized();
        log.info("Mem value: " + memoized.apply());
        log.info("Mem value: " + memoized.apply());
        log.info("Mem value: " + memoized.apply());
        log.info("Mem value: " + memoized.apply());
        log.info("Non mem value: " + randnum.apply());
        log.info("Non mem value: " + randnum.apply());
        log.info("Non mem value: " + randnum.apply());
        log.info("Non mem value: " + randnum.apply());

    }
}
