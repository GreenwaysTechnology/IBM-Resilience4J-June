package vavrprj.example.value;


import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags({@Tag("either")})
@Slf4j
public class TestEither {

    @Test
    public void testEither(){
        log.info("Test Either - BEGIN");
        Either<String, Integer> eithervalue = computeStuff(2);
        log.info("Test Either - IsLeft " + eithervalue.isLeft());
        log.info("Test Either - IsRight " + eithervalue.isRight());
        log.info("Test Either - Value " + eithervalue.getLeft());
        log.info("Test Either - isLazy " + eithervalue.isLazy());

        Either<String, Integer> eithervalueValid = computeStuff(3);

        Either<String, Integer> flatmapped = eithervalueValid.flatMap(s -> {
            if (s.intValue() == 9)
                return Either.left("9 should also not be accepted");
            else
                return Either.right(5);
        });

        log.info("9 not good: " + flatmapped);

        log.info("Test Either - IsLeft " + eithervalueValid.isLeft());
        log.info("Test Either - IsRight " + eithervalueValid.isRight());
        log.info("Test Either - Value " + eithervalueValid.get());
        log.info("Test Either - isLazy " + eithervalueValid.isLazy());

        log.info("Test Either - END");
    }


    private Either<String, Integer> computeStuff(Integer myval){
        if (myval.equals(2))
            return Either.left("We dont support the number 2");
        return Either.right(myval*myval);
    }


}
