package org.geofft;

import org.geofft.validator.ValidCustomerValidator;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;


public class TestValidator {

    Customer customer;
    ValidCustomerValidator validCustomerValidator;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Test
    public void testPayloadInComposedConstraints() {

        this.customer = new Customer();
        this.customer.setName("Bob");

        Set<ConstraintViolation<Customer>> violations = validate(this.customer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{org.geofft.constraint.NameMustBeGeoff.message}", violation.getMessage());
        assertEquals(1, violation.getConstraintDescriptor().getPayload().size());

    }

    private Set<ConstraintViolation<Customer>> validate(Customer customer) {
        Validator validator = this.factory.getValidator();
        Set<ConstraintViolation<Customer>> result =  validator.validate(customer);
        return result;
    }

}
