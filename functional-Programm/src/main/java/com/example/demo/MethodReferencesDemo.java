package com.example.demo;

import java.util.Arrays;
import java.util.List;

//
class Loop {
    //target to thread
    public static void loop() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Loop " + i);
        }
    }
}

@FunctionalInterface
interface Welcome {
    void printMessage(String message);
}


public class MethodReferencesDemo {

    Thread thread;

    //instance method holding thread logic
    public void runMe() {
        String threadName = "Loop Thread";
        System.out.println(threadName);
    }

    public void startThread() {

        //inline logic
        thread = new Thread(() -> System.out.println("Thread Started"));
        thread.start();
        //method references ;refering function inside Caller
        thread = new Thread(() -> runMe());
        thread.start();
        // instance method reference symbol ::
        thread = new Thread(this::runMe);
        thread.start();
        //passing static method as reference
        thread = new Thread(Loop::loop);
        thread.start();


    }


    public static void main(String[] args) {

        MethodReferencesDemo methodRef = new MethodReferencesDemo();
        methodRef.startThread();

        Welcome welcome = null;
        welcome = message -> {
            System.out.println(message);
        };
        welcome.printMessage("Hello");

        welcome = message -> System.out.println(message);
        welcome.printMessage("Hello");

        //code refactoring using Method Reference
        welcome = System.out::println;
        welcome.printMessage("Hello Method Reference");
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //List and Lambda with method Reference
        List<String> list = Arrays.asList("Hello", "How are you", "Welcome to Functional Style Programming");
        //functional style of iterating list
        list.forEach(message -> System.out.println(message));
        list.forEach(System.out::println);

    }
}
