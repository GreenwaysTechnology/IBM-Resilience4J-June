package demo.validation;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ValidationTest {

    @Test
    void testValidation1(){
        String nameFromForm = "Mike";
        Integer ageFromForm = -41;

        Validation<Seq<String>, Person> validationCombined = validateName(nameFromForm)
                .combine(validateAge(ageFromForm))
                .ap((s, i) -> new Person(s, i));

        log.info("Validation Combined Result: " +validationCombined);
        Person maybeMikeMaybeJohn = validationCombined.getOrElseGet((ls) -> new Person("John", 50));
        log.info("Person returned: " +maybeMikeMaybeJohn);
    }

    private Validation<String, String> validateName(String name){
        if (name.length() == 0 || name.length() > 8)
            return Validation.invalid("The Name you enter must be between 0 and 8 characters long");
        return Validation.valid(name);
    }

    private Validation<String, Integer> validateAge(Integer age){
        if (age < 20 || age > 100){
            return Validation.invalid("Age must be between 20 and 100");
        }
        return Validation.valid(age);
    }

}
