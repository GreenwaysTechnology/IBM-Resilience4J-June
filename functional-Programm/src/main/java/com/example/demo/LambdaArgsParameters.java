package com.example.demo;

@FunctionalInterface
interface Single {
    void setName(String name);
}

//Two or more parameters
@FunctionalInterface
interface Concatation {
    void concat(String firstName, String lastName);
}

//ReturnValues
@FunctionalInterface
interface Producer {
    String produce();
}

@FunctionalInterface
interface SupplyAndProduce {
    String accept(String something);
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}


public class LambdaArgsParameters {

    public static void main(String[] args) {
        //Pass single parameter
        Single single = null;
        //pass single Variable
        single = (String name) -> {
            System.out.println(name);
            System.out.println("Single Interface");
        };
        single.setName("Subramanian");
        //code Refactoring 1: if function body has one line of code: remove {}
        single = (String name) -> System.out.println(name);
        single.setName("Subramanian");
        //code Refactoring 2: if function arg has variable with type
        //       Type inference : the type is implicitly understood.
        single = (name) -> System.out.println(name);
        single.setName("Subramanian");
        //code Refactoring 3: if function single arg has variable with type :drop ()
        single = name -> System.out.println(name);
        single.setName("Subramanian");

        //Two parameters
        Concatation concatation = (firstName, lastName) -> System.out.println(firstName + lastName);
        concatation.concat("Subramanian", "Murugan");

        Producer producer = null;
        producer = () -> {
            return "SOmething has produced";
        };
        System.out.println(producer.produce());

        //code Refactoring 1: if function body has only return statement, no more code: remove {} and return statement
        producer = () -> "SOmething has produced";
        System.out.println(producer.produce());

        //take and return the same
        SupplyAndProduce sp = something -> something;
        System.out.println(sp.accept("something is taken and returned"));

        Calculator calc = (a, b) -> {
            int c = a + b;
            return c;
        };
        System.out.println(calc.calculate(10, 20));

    }
}
