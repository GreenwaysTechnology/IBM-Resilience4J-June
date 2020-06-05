package vavrprj.example.match;

import io.vavr.API;
import io.vavr.Predicates;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static io.vavr.API.*;

@Tags({@Tag("match")})
@Slf4j
public class TestMatch {

    @Test
    void testMatch(){
        Integer i = 10;

        Integer of1 = Match(i).of(
                Case($(1), 10),
                Case($(), 20)
        );
        String matched = Match(i).of(
                Case($(1), "værdien eet"),
                Case($(2), "value two")
                , Case($(), "default value... ?")
        );

        log.info("matched: " + matched);

        Option<String> match2 = Match(i).option(
                Case($(1), "værdien eet"),
                Case($(2), "value two")
        );

        i = 3;
        Void of = Match(i).of(
                Case($(2), v -> run(this::doStuff)),
                Case($(1), v -> run(this::doStuff)),
                Case($(), v -> run(this::doStuff))
        );
        log.info("match3: " + of);
    }

    private void doStuff(){
    }
}
