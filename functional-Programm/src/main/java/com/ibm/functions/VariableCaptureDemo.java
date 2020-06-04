package com.ibm.functions;

interface Variable {
    void doIt();
}

public class VariableCaptureDemo {

    //instance variable
    private int counter;

    public void increment() {
        //Lambda will access the counter variable
        Variable variable = () -> {
            System.out.println(++counter);
        };
        variable.doIt();
    }

    public static void main(String[] args) {

        VariableCaptureDemo inc = new VariableCaptureDemo();
        for (int i = 0; i < 9; i++) {
            inc.increment();
        }
        //local variable
        String name = "Subramanian";
        Variable variable = () -> {
            //local variable inside lambda
            System.out.println(name);
        };
        variable.doIt();

        //Effective final : implictly final
        // final int newCounter = 0;
        int newCounter = 1;
        Variable variable1 = () -> {
            System.out.println(newCounter * 10);
            System.out.println(newCounter + 1);
        };
        variable1.doIt();


    }
}
