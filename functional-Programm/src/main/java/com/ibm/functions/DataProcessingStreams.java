package com.ibm.functions;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProcessingStreams {

    public static void main(String[] args) {
        //Data Processing : array ,List : data Souce
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,561);
        //feed datasource into stream and start processing : using obser + push + functionalstyle
        //create Stream from list
        Observable<Integer> numberStream = Observable.fromIterable(numbers);

        //processing data : i want to return new stream with double of that
        numberStream
                .map(x -> x * 3)
                .filter(e -> e % 2 != 0)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("Stream is completed!");
                });

    }
}
