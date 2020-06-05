package demo.function;


import io.vavr.*;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
public class FunctionTest {

    @Test
    void functionTest(){
        Function4<String, Integer, String, Integer, Integer> f4 =
                (s1, i1, s2, i2) -> s1.length() + i1 + s2.length() + i2;
        Integer result = f4.apply("hello", 50, "world", 100);
        log.info("result: " + result);

        Function3<Integer, String, Integer, Integer> f3 = f4.apply("Denmark");
        Function2<String, Integer, Integer> f2 = f3.apply(20);
        mymethod(f2);
    }

    void mymethod(Function2<String, Integer, Integer> f2){
        Integer umbrella = f2.apply("Umbrella", 100);
        log.info("umbrella result: " + umbrella);
    }

    @Test
    void functionTestCurried(){
        Function4<String, Integer, String, Integer, Integer> f4 =
                (s1, i1, s2, i2) -> s1.length() + i1 + s2.length() + i2;
        Function1<String, Function1<Integer, Function1<String, Function1<Integer, Integer>>>> curried = f4.curried();
        Integer apply = f4.apply("Curried")
                .apply(40)
                .apply("vavr")
                .apply(50);
        log.info("apply: " + apply);
    }


    @Test
    void functionTestMemoize(){
        Function0<Integer> f0 = () -> new Random().nextInt();
        log.info("f0 result: " + f0.apply());
        log.info("f0 result: " + f0.apply());
        log.info("f0 result: " + f0.apply());
        Function0<Integer> memoized = f0.memoized();
        log.info("memoized result: " + memoized.apply());
        log.info("memoized result: " + memoized.apply());
        log.info("memoized result: " + memoized.apply());
    }


    @Test
    void functionTestLift(){
        Function2<Integer, Integer, Integer> divide = (i1, i2) -> i1/i2;
        Function2<Integer, Integer, Option<Integer>> lift = Function2.lift(divide);
        Option<Integer> result = lift.apply(9, 3);
        log.info("result: " + result);
    }

    @Test
    void functionComposite(){
        Function2<Integer, Integer, Integer> divide = (i1, i2) -> i1/i2;
        Function1<Integer, Integer> multiply = (i) -> i*10;
        Function2<Integer, Integer, Integer> fcomposite = divide.andThen(multiply);
        Integer result = fcomposite.apply(9, 3);
        log.info("Result " + result);


    }



}
