package com.example.demo;

//before java 8


/**
 * interface can hold only static variables + abstract methods
 * provided methods with implementation
 */

//SAM : SINGLE Abstract methods :functional interface
interface MyFunction {
    void apply(); //abstract method

    default void saySomething() {
        System.out.println("Say something");
    }

    static void doSomething() {
        System.out.println("Do somethig");
    }
}

/**
 * class myfunctionImpl implements MyFunction{
 * apply
 * saySomething
 * doSomething
 * }
 * MyFunction func = new myfunctionImpl()
 * func.apply
 * func.saySomething
 * MyFunction.doSomething
 */

//Interface contractor writes functional interfaces
//certify this interface is 100% functional interface

@FunctionalInterface
interface MyFunction2 {
    void consume();

    default void saySomething() {
        System.out.println("Say something");
    }

    static void doSomething() {
        System.out.println("Do somethig");
    }
}


public class AdvancedLambda {
    public static void main(String[] args) {
        MyFunction func = () -> {
            System.out.println("My Function");
        };
        func.apply();
        func.saySomething();
        MyFunction.doSomething();

        //when
        MyFunction2 function2 = () -> {
            System.out.println("consumer");
        };
        function2.consume();

        //create threads using lambda
        Thread t1 = null;

        //old style :
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t1.start();
        //lambda
        t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        t1.start();


    }
}
