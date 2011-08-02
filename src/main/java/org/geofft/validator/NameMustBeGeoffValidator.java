package org.geofft.validator;

import org.geofft.Customer;
import org.geofft.constraint.NameMustBeGeoff;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameMustBeGeoffValidator implements ConstraintValidator<NameMustBeGeoff, Customer> {

    @Override
    public void initialize(NameMustBeGeoff constraintAnnotation) {
    }

    @Override
    public boolean isValid(Customer value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (value.getName() != null) {
            if (!value.getName().equals("Geoff")) {
                isValid = false;
            }
        }
        return isValid;
    }

}
