package com.ibm.reactive;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class FlowableStream {
    public static void main(String[] args) {
        //Flowable === Observable + BackPressure
        Flowable<Integer> sourcestream = Flowable.range(1, 1000);
        sourcestream.map(element -> element * 2).subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Stream is completed!");
        });

        //Backpressure Strategy :Buffer
        Flowable<Integer> flowStream = Flowable.create(source->{
            for(int i=0;i<2000;i++){
                source.onNext(i);
            }
        }, BackpressureStrategy.BUFFER);
        //Buffers all values until downstream consumes it
        flowStream.subscribe(System.out::println);
    }
}
