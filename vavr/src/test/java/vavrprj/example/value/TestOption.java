package vavrprj.example.value;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@Tags({@Tag("option")})
@Slf4j
public class TestOption {

    @Test
    void testOption(){

        Option<String> test123 = Option.of("test123");
        log.info("String: " + test123);
        Option<String> sflatmapped = test123.flatMap((s) -> Option.of((String) null));
        log.info("String flatmapped: " + sflatmapped);

        Option<String> snull = test123
                .map((sv) -> (String)null)
                .map((s) -> s.toLowerCase());
        log.info("String snull: " + snull);
    }

    @Test
    void test2(){
        Optional<String> optstr = Optional.of("sadfds")
                .map((s) -> (String)null)
                .map(s -> s.toUpperCase());
        log.info("optStr: " + optstr + ", value: " + optstr.orElseGet(() -> "sfdsa"));

        Option<String> vavropt = Option.of("sadfdsa")
                .flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase());

        log.info("vavropt: " + vavropt);
    }
}
