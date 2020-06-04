package com.ibm.reactive;

import io.reactivex.Observable;

public class GreeterStream {
    public static void main(String[] args) {
        Observable greeterStream = Observable.create(producer -> {
            producer.onNext("Greet"); // data pushing
            //you can inform , stream is close
            producer.onComplete();
        });

        //subscribers
//        greeterStream.subscribe(data -> {
//            System.out.println(data);
//        }, err -> {
//            System.out.println(err);
//        }, () -> {
//            System.out.println("Stream is completed!");
//        });
        greeterStream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Stream is completed!");
        });
    }
}
