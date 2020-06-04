package com.ibm.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {

//        Consumer cns = new Consumer() {
//            @Override
//            public void accept(Object test) {
//
//            }
//        }
        //Consumer use case
        Consumer<Integer> integerConsumer = null;
        //just take and print
        integerConsumer = value -> System.out.println(value);
        integerConsumer.accept(100);

        integerConsumer = System.out::println;
        integerConsumer.accept(100);

        integerConsumer = value -> {
            Integer sum = value + 200;
            System.out.println("Sum : " + sum);
        };
        integerConsumer.accept(100);
        //Consumer used inside collections itertors
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.forEach(System.out::println);
    }
}
