package demo.function;

import io.vavr.Function0;
import io.vavr.Lazy;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ValueTest {


    @Test
    void testTry() {
        Try<String> proevAtFaaVejrudsigt = Try.of(() -> vejrUdsigt(20, 10));
        Try<String> recover = proevAtFaaVejrudsigt.recover((e) -> "tjaaa måske hagl " + e.getMessage());
        log.info("resultat: " + proevAtFaaVejrudsigt);
        log.info("resultat recovered: " + recover);
    }

    String vejrUdsigt(Integer lufttryk, Integer temperatur) throws SkidtVejrException{
        if (lufttryk <= 0 || temperatur <= 0)
            throw new IllegalArgumentException("Hov hov de værdier kan vi ikke bruge");

        if (temperatur > 20 && lufttryk > 20)
            return "Solskin!!!";

        if (temperatur < 8)
            throw new SkidtVejrException();

        return "Sådan lidt blandet";
    }


    @Test
    void testEither(){
        log.info(meaningOflife(1).toString());
    }

    Either<String, Integer> meaningOflife(Integer age){
        if (age <= 0)
            return Either.left("Hov hov det ved vi ikk pga alderen.");
        return Either.right(42);
    }


    @Test
    void testLazy(){
//        Lazy<Double> of = Lazy.of(() -> Math.random());
        Function0<Double> of = () -> Math.random();

        log.info("" + of.get());
        log.info("" + of.get());
        log.info("" + of.get());
        log.info("" + of.get());
        log.info("" + of.get());
    }


    @Test
    void testStream(){
        for (Double i : Stream.continually(() -> Math.random()).take(100))
        {
            log.info("" + i);
        }
        List<String> mylist = List.of("adsfdsa", "addfds");

        java.util.List<String> strings = mylist.asJava();
    }


}
