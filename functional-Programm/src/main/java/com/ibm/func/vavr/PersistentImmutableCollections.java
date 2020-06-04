package com.ibm.func.vavr;

import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.collection.TreeSet;

import java.util.Arrays;


public class PersistentImmutableCollections {
    public static void main(String[] args) {
        //Create Linked
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        System.out.println(list1.hashCode());
        //list operations : whether list is persistent and pure immutable
        List<Integer> list2 = list1.tail();
        System.out.println(list2.hashCode());

        List<Integer> list3 = list2.prepend(6);
        System.out.println(list3.hashCode());

        //functional
        System.out.println(List.of(10, 89).tail().prepend(3));


        //functional style list processing : stream processing
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(num -> num % 2 == 0)
                .forEach(System.out::println);

        //Remaining Data structure
        Queue.of();
        TreeSet.of(12);

        //i want to filter all even numbers and sum of all even of even numbers

        //java 8 stream style
        int sumResult = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .filter(num -> num % 2 == 0).reduce((i, j) -> i + j).get();

        System.out.println("Sum using java 8 stream  api " + sumResult);

        //vaVr
        System.out.println("SUM using Vavr" + List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(num -> num % 2 == 0)
                .sum());


    }
}
