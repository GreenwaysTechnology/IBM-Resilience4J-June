package vavrprj.example.value;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags({@Tag("validation")})
@Slf4j
public class TestValidation {
    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    @Test
    void testValidation(){
        Validation<Seq<String>, Person> mike = validatePerson("Magic Mike !!_", -122);
        log.info("invalid person: " + mike);
        Seq<String> error = mike.getError();
        String reduced = error.reduce((s1, s2) -> s1 + "===" + s2);
    }

    private Validation<Seq<String>, Person> validatePerson(String name, Integer age){
        Validation<Seq<String>, Person> ap =
                Validation.combine(validateNameChars(name), validateAge(age))
                .ap((s, i) -> new Person(s,i));
        return ap;
    }

    private Validation<String, String> validateNameChars(String name) {
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: '"
                + seq.distinct().sorted() + "'"));
    }

    private Validation<String, Integer> validateAge(Integer age){
        if (age < 20 || age > 60 || age < 0)
            return Validation.invalid("Age not in correct range");
        return Validation.valid(age);
    }

}
