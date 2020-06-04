package com.ibm.func.vavr;


import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;

public class FunctionalPrinciple {
    public static void main(String[] args) {

        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        //upto 8
        Function1<Integer, Integer> addAndMultiply = multiplyByTwo.compose(plusOne);
        Function1<Integer, Integer> addAndThen = multiplyByTwo.andThen(plusOne);

        System.out.println(addAndMultiply.apply(2));
        System.out.println(addAndThen.apply(2));

        //Options Value
        Option<Integer> option = Option.of(10);
        System.out.println(option.get());

        //Options without value
        //opption1 with value,optoin2 with null => option3
        //on which we can use, filter,map,flatMap --operators : iterable operator
        //eg List,array iterable objects ; for..
        //why Option is iterable -- Option inherits Value---inherits--Iterable
        Option valueIs = Option.of("subramanian");
        Option<String> valueMaybe = valueIs.
                flatMap(s -> Option.of((String) null)).map(s -> s.toString() + "default");
        System.out.println(valueMaybe.isEmpty());

        //Lifting : extract a function from existing
        //source function
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Function2<Integer, Integer, Option<Integer>> partial = Function2.lift(divide);

        //NONE
        Option<Integer> noneResult = partial.apply(10, 0);
        System.out.println("None Result" + noneResult);
        System.out.println("None Result" + noneResult.getOrElse(1));


        //SOME
        Option<Integer> someResult = partial.apply(10, 2);
        System.out.println("Some Result" + someResult.get());


    }
}
