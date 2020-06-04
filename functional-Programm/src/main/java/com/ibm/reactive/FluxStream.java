package com.ibm.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxStream {
    public static void main(String[] args) {
        Flux<String> stream = Flux.create(producer -> {
            producer.next("Subramanian");
            producer.next("ram");
            producer.next("raja");
            producer.next("geetha");
            producer.complete();
        });
        stream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Flux completed");
        });

        Flux<Integer> intStream = Flux.range(1, 10);
        intStream.map(item -> item * 3)
                .filter(e -> e % 2 == 0)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("Flux completed");
                });
        Mono.just("One data").subscribe(System.out::println);
        Mono.empty().subscribe(null, null, () -> System.out.println("Mono Completed"));
        Mono.error(new Throwable("Err")).subscribe(System.out::println);


    }
}
