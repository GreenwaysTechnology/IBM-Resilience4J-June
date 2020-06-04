package com.ibm.func.vavr;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

import static io.vavr.API.*;

public class MainVavr {
    public static void testCollections() {
        //immutable
        List<Integer> mylist = List.of(1, 2, 3, 100);
        List<Integer> newList = mylist.append(200);
        for (Integer myiterator : newList) {
            System.out.println("value: " + myiterator);
        }
    }

    public static void testStream() {
        Stream<Integer> take = Stream.from(5)
                .filter((n) -> n % 2 == 0)
                .take(10);

        take.forEach(System.out::println);
        System.out.println(" SUM : " + take.sum());
    }

    public static void testMap() {
        HashMap<String, Integer> map = HashMap.of("key1", 10, "key2_twenty", 20, "key3", -1000);
        System.out.println("Map: " + map);

        Tuple2<String, String> t1 = Tuple.of("key1", "value1");
        Tuple2<String, String> t2 = Tuple.of("key2", "value2");
        Tuple2<String, String> t3 = Tuple.of("key3", "value3");
        HashMap<String, String> mapFromTuples = HashMap.of(t1).put(t2).put(t3);
        System.out.println("Map from tuples " + mapFromTuples);
    }

    private static Either<String, Integer> calculateStuff(Integer argument1) {
        if (argument1 <= 0)
            return Either.left("The argument value should be more than zero. \nThe argument you passed was: " + argument1);
        return Either.right(argument1 * 10);
    }

    public static void testEither() {
        Either<String, Integer> eitherResult = calculateStuff(-2);
        System.out.println("Result: " + eitherResult);

        if (eitherResult.isLeft()) {
            String left = eitherResult.getLeft();
            System.out.println("left value: " + left);
        }

        if (eitherResult.isRight()) {
            Integer right = eitherResult.get();
            System.out.println("right value: " + right);
        }
    }

    public static void main(String[] args) {
        testCollections();
        testStream();
        testMap();
        testEither();
        String s = Match(100).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        System.out.println(s);


        Function1<String, String> toUpper = (str) -> {
            if (s.isEmpty()) throw new IllegalArgumentException("input can not be null");
            return s.toUpperCase();
        };

        Function1<String, String> trim = String::trim;
        Function1<String, String> cheers = (str1) -> String.format("Hello %s", s);
        Function1<String, String> composedCheer = cheers.compose(trim).compose(toUpper);
        Function1<String, Option<String>> lifted = Function1.lift(composedCheer);
    }
}
