package com.ibm.functions;

@FunctionalInterface
interface Handler {
    String handle(String connectioninfo);
}
//passing more functions as parameter

@FunctionalInterface
interface Resolve {
    void resolve(Object data);
}

@FunctionalInterface
interface Reject {
    void reject(Object error);
}

@FunctionalInterface
interface Validator {
    void validate(Resolve resolve, Reject reject);
}


class HttpServer {

    public void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    //server apis
    public void start(Handler handler) {
        //some dealy
        System.out.println("Server is starting");
        delay(5000);
        System.out.println(handler.handle("Port : 8080 Host : ibmhost"));
    }

    public void stop(Handler handler) {
        System.out.println("Server is stoping");
         delay(2000);
        System.out.println(handler.handle("Port : 8080 Host : ibmhost"));
    }

}


public class FunctionAsArgAndParam {

    public static void main(String[] args) {

        HttpServer httpServer = null;

        httpServer = new HttpServer();
        //call start method : old style
        httpServer.start(new Handler() {
            @Override
            public String handle(String connectioninfo) {
                return "HTTP Server is up " + connectioninfo;
            }
        });
        //passing lambda function
        httpServer.start(connectioninfo -> {
            return "Http Server up again " + connectioninfo;
        });
        //function as parameter
        httpServer.start(connectioninfo -> "Http Server up again " + connectioninfo);

        //stop method
        httpServer.stop(connectioninfo -> "Http Server Stoped " + connectioninfo);

        // Resolve resolve = () -> "SUCCESS";
        // Reject reject = () -> "FAILED";


        Validator validator = (res, rej) -> {
            //  String fakeData = null;
            String fakeData = "Subramanian";
            if (fakeData != null) {
                res.resolve(fakeData);
            } else {
                rej.reject("No User Found");
            }
        };
        //validator.validate(resolve,reject);
        validator.validate(response -> {
            System.out.println(response);
        }, err -> {
            System.out.println(err);
        });

    }
}
