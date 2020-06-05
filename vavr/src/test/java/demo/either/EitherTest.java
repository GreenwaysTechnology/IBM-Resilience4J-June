package demo.either;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class EitherTest {

    @Test
    void testEitherTest1() {
        Either<String, Integer> eitherResult = calculateStuff(-2);
        log.info("Result: " + eitherResult);

        if (eitherResult.isLeft()) {
            String left = eitherResult.getLeft();
            log.info("left value: " + left);
        }

        if (eitherResult.isRight()) {
            Integer right = eitherResult.get();
            log.info("right value: " + right);
        }
    }

    private Either<String, Integer> calculateStuff(Integer argument1) {
        if (argument1 <= 0)
            return Either.left("The argument value should be more than zero. \nThe argument you passed was: " + argument1);
        return Either.right(argument1 * 10);
    }
}
