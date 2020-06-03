package com.example.demo;

//How to begin with lambda calculs : function abstraction

interface Greeter {
    abstract void greet(); // abstract method
}
//how to expose greet method(function) implementations

//way 1- create a separate class , implements that
class GreeterImpl implements Greeter {
    @Override
    public void greet() {
        System.out.println("Hello from Greeter Impl Class");
    }
}


public class LambdaIntro {
    public static void main(String[] args) {

        //create Object
        Greeter greeter = new GreeterImpl();
        greeter.greet();
        //Problems with the above implementation :  we have to maintain separate code. and what if i want dynamic implementation//of code.//Soultion : anonmous inner class

        //way 2 : inner classes
        Greeter greeter1 = null;
        greeter1 = new Greeter() {
            @Override
            public void greet() {
                System.out.println("Hello");
            }
        };
        greeter1.greet();
        greeter1 = new Greeter() {
            @Override
            public void greet() {
                System.out.println("Hai");
            }
        };
        greeter1.greet();
        //functional programming : Lambda Expression
        //way 3
        Greeter greetfunc = () -> {
            System.out.println("Hai");
        };
        greetfunc.greet();


    }

}
