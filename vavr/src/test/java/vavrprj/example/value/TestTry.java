package vavrprj.example.value;


import io.vavr.*;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

@Tags({@Tag("try")})
@Log
public class TestTry {

    @Test
    public void testTry(){
        log.info("Test try - BEGIN");
        Try<String> maybefailure = Try.of(this::somework);
        log.info("success:" + maybefailure.isSuccess() + ", value: " + maybefailure.get());

    }

    private String somework() throws IOException{
        Integer myint = 2;
        if (myint == 2)
            throw new IOException("We dont accept the number 2 as argument");
        return "Test";
    }
}
