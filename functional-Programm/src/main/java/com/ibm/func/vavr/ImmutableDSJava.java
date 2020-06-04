package com.ibm.func.vavr;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ImmutableDSJava {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello");
        List<String> newList = Collections.unmodifiableList(list);

        //try to add new element on new list
        newList.add("Hai");

    }
}
