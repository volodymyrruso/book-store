package org.mate.bookstorespringboot.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.mate.bookstorespringboot.validation.annotations.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 20;
    private static final Pattern AT_LEAST_ONE_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern AT_LEAST_ONE_UPPERCASE_LETTER = Pattern.compile(".*[A-Z].*");
    private static final Pattern AT_LEAST_ONE_LOWERCASE_LETTER = Pattern.compile(".*[a-z].*");
    private static final Pattern AT_LEAST_ONE_SPECIAL_CHAR =
            Pattern.compile(
            ".*[!”$&’()*+,-.:;=\\[\\]\\\\^_`{|}~]+.*");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^[A-Za-z0-9!”$&’()*+,-.:;=\\[\\]\\\\^_`{|}~]*$"
    );

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null
                && password.length() >= MIN_LENGTH
                && password.length() <= MAX_LENGTH
                && PASSWORD_PATTERN.matcher(password).matches()
                && AT_LEAST_ONE_DIGIT.matcher(password).matches()
                && AT_LEAST_ONE_UPPERCASE_LETTER.matcher(password).matches()
                && AT_LEAST_ONE_LOWERCASE_LETTER.matcher(password).matches()
                && AT_LEAST_ONE_SPECIAL_CHAR.matcher(password).matches();
    }
}
