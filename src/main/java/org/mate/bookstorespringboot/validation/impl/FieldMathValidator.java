package org.mate.bookstorespringboot.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;
import org.mate.bookstorespringboot.validation.annotations.FieldMatch;

public class FieldMathValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstField = constraintAnnotation.first();
        this.secondField = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object firstObj = getFieldValue(value, firstField);
            Object secondObj = getFieldValue(value, secondField);

            return Objects.equals(firstObj, secondObj);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Annotation does not work well. Check annotations properties.", e);
        }
    }

    private Object getFieldValue(Object o, String fieldName) throws ReflectiveOperationException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }
}
