package com.ibm.func.vavr;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

import io.vavr.control.Try;


public class TryTest {

    public static void main(String[] args) {
        TryTest tryTest = new TryTest();
        tryTest.tryTestBusinessStuff();
    }

    void tryTestBusinessStuff() {
        Try<Integer> result = Try.of(this::calculateImportantStuff);
        System.out.println("result: " + result);
        //System.out.println(result.get());
        // System.out.println(result.getOrElse(0));
        //   result.onFailure(failed-> System.out.println(failed.getMessage()));
//        Integer fresult = result.recover(BusinessException.class, 0)
//                .recover(OtherBusinessException.class, 1)
//                .get();
//        System.out.println(fresult);

        int newResult = result.recover(x -> Match(x).of(
                Case($(instanceOf(BusinessException.class)), t -> 80),
                Case($(instanceOf(OtherBusinessException.class)), t -> 30)
        )).getOrElse(90);
        System.out.println(newResult);


    }

    private Integer calculateImportantStuff() throws BusinessException, OtherBusinessException {
        if (1 == 1)
            throw new BusinessException();
        if (1 == 2)
            throw new OtherBusinessException();
        return 42;
    }

}
