package com.ibm.reactive;

import io.reactivex.Observable;

class UserService {
    public Observable streamUsers() {
        Observable stream = Observable.create(producer -> {
            String userName = "admin";
            String password = "admin";
            if (userName.equals(userName) && password.equals("adminxx")) {
                producer.onNext("Login Successfull");
                producer.onComplete();
            } else {
                producer.onError(new RuntimeException("User Not Found"));
            }
        });

        return stream;
    }
}


public class UserAuthStream {
    public static void main(String[] args) {
        new UserService().streamUsers().subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Stream is completed!");
        });
    }
}

