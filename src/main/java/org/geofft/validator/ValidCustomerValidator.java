package org.geofft.validator;

import org.geofft.Customer;
import org.geofft.constraint.ValidCustomer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class ValidCustomerValidator implements ConstraintValidator<ValidCustomer,Customer> {

    @Override
    public void initialize(ValidCustomer constraintAnnotation) {
    }

    @Override
    public boolean isValid(Customer value, ConstraintValidatorContext context) {
        return true;
    }

}
