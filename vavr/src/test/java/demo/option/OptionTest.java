package demo.option;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@Slf4j
public class OptionTest {

    @Test
    void optionTest(){
        Optional<String> opt = Optional.of("MyValue");
        Optional<String> java8result = opt.map(s -> (String) null)
                .map(s -> s.toUpperCase());
        log.info("java8result: " + java8result);
        Option<String> vavroption = Option.of("MyVavrValue")
                .map(s -> s.toUpperCase());
        log.info("vavr-result: " + vavroption);

    }
}
