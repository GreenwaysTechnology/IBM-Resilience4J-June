package vavrprj.example.value;


import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Tags({@Tag("lazy")})
@Slf4j
public class TestLazy {

    @Test
    public void testTry(){
        log.info("Test Lazy - BEGIN");
        Lazy<Integer> magicnumber = Lazy.of(() -> new Random().nextInt());
        log.info("Is evaluated " + magicnumber.isEvaluated());
        log.info("magic number:" + magicnumber.get());
        log.info("magic number:" + magicnumber.get());
        log.info("magic number:" + magicnumber.get());
        log.info("magic number:" + magicnumber.get());
        log.info("Is evaluated " + magicnumber.isEvaluated());

        Function0<Integer> fmagic = Function0.of(() -> new Random().nextInt());
        log.info("magic from function --> " + fmagic.get());
        log.info("magic from function --> " + fmagic.get());
        log.info("magic from function --> " + fmagic.get());
        log.info("magic from function --> " + fmagic.get());
        log.info("magic from function --> " + fmagic.get());
        log.info("Test Lazy - END");
    }




}
