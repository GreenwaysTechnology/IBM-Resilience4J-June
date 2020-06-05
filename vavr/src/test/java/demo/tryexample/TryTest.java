package demo.tryexample;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class TryTest {

    @Test
    void tryTestBusinessStuff(){
        Try<Integer> result = Try.of(this::calculateImportantStuff);
        log.info("result: " + result);
        Try<Integer> recover = result.recover(BusinessException.class, 10)
                .recover(OtherBusinessException.class, 20);
        log.info("Recovered result!: " + recover);


        Integer orElse = result.getOrElse(50);
        log.info("Get or else!: " + orElse);

        Integer orElseRecovered = recover.getOrElse(50);
        log.info("Recovered Get or else!: " + orElseRecovered);


    }

    private Integer calculateImportantStuff() throws BusinessException, OtherBusinessException {
        if (1==2)
            throw new BusinessException();
        if (1==1)
            throw new OtherBusinessException();
        return 42;
    }

}
