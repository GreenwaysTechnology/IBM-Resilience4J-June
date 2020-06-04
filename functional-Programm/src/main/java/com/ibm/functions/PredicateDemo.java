package com.ibm.functions;

import java.util.function.Predicate;

class FitlerApi {

    public boolean filter(int input) {
        //conditions checking via if ..else lader : we can use functional style of condition
        Predicate<Integer> condition = (value) -> value == 100;
        return condition.test(input); // return boolean result ? can we grab the result through lambda
    }

}

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer> greaterThan = null;
        greaterThan = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 100; // should return always boolean
            }
        };
        System.out.println(greaterThan.test(1000) ? "Passed" : "Failed");
        greaterThan = value -> value > 1000;
        System.out.println(greaterThan.test(1000) ? "Passed" : "Failed");

        FitlerApi filterapi = new FitlerApi();
        //System.out.println(filterapi.filter(100) ? "Passed" : "Failed");

        //take the result with handlers:
    }
}
