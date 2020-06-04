package com.ibm.functions;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {

        Function<Long, Long> adder = value -> value + 3;
        Long result = adder.apply(1000l);
        System.out.println("Result :" + result);
        //Function composition : Higher Order function : looks like decorator pattern in java
        //on existing function new functons
        //fun1.compose(fun2) => func3
        Function<Integer, Integer> multiply = value -> {
            System.out.println("Multiply function called");
            return value * 2;
        };
        Function<Integer, Integer> add = value -> {
            System.out.println("Add function is called!");
            return value + 3;
        };
        //compose theses functions : right to left decoration
        Function<Integer, Integer> func = multiply.compose(add);
        System.out.println(func.apply(3));
        //compose theses functions : left to right  decoration
        Function<Integer, Integer> func1 = multiply.andThen(add);
        System.out.println(func1.apply(3));
    }
}
