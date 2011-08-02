package org.geofft;

import org.junit.*;

import javax.validation.*;
import javax.validation.constraints.NotNull;

import java.lang.annotation.*;
import java.util.*;

import static org.junit.Assert.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

public class TestPayload {

    public static class Warning implements Payload {}
    public static class Error implements Payload {}

    @Documented
    @Constraint(validatedBy = NameMustBeGeoffValidator.class)
    @Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    public @interface NameMustBeGeoff {
        String message() default "{NameMustBeGeoff.message}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class NameMustBeGeoffValidator implements ConstraintValidator<NameMustBeGeoff, Customer> {
        @Override
        public void initialize(NameMustBeGeoff constraintAnnotation) {}
        @Override
        public boolean isValid(Customer value, ConstraintValidatorContext context) {
            return ((value.getName() == null) || (value.getName().equals("Geoff")));
        }
    }

    @NameMustBeGeoff(payload = Error.class)
    @Documented
    @Constraint(validatedBy = ValidCustomerValidator.class)
    @Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    public @interface ValidCustomer {
        String message() default "{ValidCustomer.message}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class ValidCustomerValidator implements ConstraintValidator<ValidCustomer, Customer> {
        @Override
        public void initialize(ValidCustomer constraintAnnotation) {}
        @Override
        public boolean isValid(Customer value, ConstraintValidatorContext context) { return true; }
    }

    @ValidCustomer
    public class Customer {
        private String name;

        public Customer(String name) { this.name = name; }

        @NotNull(payload = Warning.class)
        public String getName() { return this.name; }
    }

    /**
     * Customer's name is null, it should trigger the @NotNull with a Warning payload
     */
    @Test
    public void test() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Customer customer = new Customer(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("may not be null", violation.getMessage());
        assertEquals("javax.validation.constraints.NotNull", violation.getConstraintDescriptor().getAnnotation().annotationType().getName());
        assertEquals(1, violation.getConstraintDescriptor().getPayload().size());
        assertEquals(Warning.class, violation.getConstraintDescriptor().getPayload().iterator().next());
    }

    /**
     * Customer's name is not Geoff and should trigger the NameMustBeGeoff constraint, but the Error payload is lost
     */
    @Test
    public void testPayloadInComposedConstraints() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Customer customer = new Customer("Bob");

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{NameMustBeGeoff.message}", violation.getMessage());
        assertEquals("org.geofft.TestPayload$NameMustBeGeoff", violation.getConstraintDescriptor().getAnnotation().annotationType().getName());

        assertEquals(0, violation.getConstraintDescriptor().getPayload().size()); // TODO: *** THIS IS WRONG ***

        /*

        What should happen:

        assertEquals(1, violation.getConstraintDescriptor().getPayload().size());
        assertEquals(Customer.Error.class, violation.getConstraintDescriptor().getPayload().iterator().next());

        */
    }

}
