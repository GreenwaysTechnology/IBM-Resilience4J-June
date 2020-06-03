package com.example.demo;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {

        List<Integer> list1 = List.of(1, 2, 3);
        System.out.println(list1.hashCode());
        List<Integer> list2 = list1.tail().prepend(1000);
        System.out.println(list2.hashCode());
        Queue.of(1, 2, 3).enqueue(4).enqueue(5);
        Stream.of(1, 2, 3).map(Object::toString).forEach(System.out::println);

        //function composition
        //You can compose functions. In mathematics, function composition is the application of one function to the result of another to produce a third function.
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        System.out.println(add1AndMultiplyBy2.apply(10).doubleValue());

        //Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);
        //then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        Function1 add1AndMultiplyBy2_test = multiplyByTwo.compose(plusOne);

        System.out.println(add1AndMultiplyBy2_test.apply(1));

        //log.info("Simple log statement with inputs {}, {} and {}", 1, 2, 3);


    }
}
