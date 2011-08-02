package org.geofft;

import org.junit.*;
import javax.validation.*;
import java.util.*;

import static org.junit.Assert.*;

public class TestValidCustomer {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Set<ConstraintViolation<Customer>> validate(Customer customer) {
        Validator validator = this.factory.getValidator();
        return validator.validate(customer);
    }

    /**
     * The one constraint on the Customer have a Warning payload.
     */
    @Test
    public void testPayloadInGetterConstraints() {

        Customer customer = new Customer(null);

        Set<ConstraintViolation<Customer>> violations = validate(customer);
        assertEquals(1, violations.size());

        for (ConstraintViolation<Customer> violation : violations) {
            assertEquals("may not be null", violation.getMessage());
            assertEquals("javax.validation.constraints.NotNull", violation.getConstraintDescriptor().getAnnotation().annotationType().getName());
            assertEquals(1, violation.getConstraintDescriptor().getPayload().size());
            assertEquals(Customer.Warning.class, violation.getConstraintDescriptor().getPayload().iterator().next());
        }
    }

    /**
     * Customer's name is not Geoff and should trigger the NameMustBeGeoff constraint, but the payload is lost
     */
    @Test
    public void testPayloadInComposedConstraints() {

        Customer customer = new Customer("Bob");

        Set<ConstraintViolation<Customer>> violations = validate(customer);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{org.geofft.constraint.NameMustBeGeoff.message}", violation.getMessage());
        assertEquals("org.geofft.constraint.NameMustBeGeoff", violation.getConstraintDescriptor().getAnnotation().annotationType().getName());

        assertEquals(0, violation.getConstraintDescriptor().getPayload().size()); // TODO: *** THIS IS WRONG ***

        /*

        This is what should happen:

        assertEquals(1, violation.getConstraintDescriptor().getPayload().size());
        assertEquals(Customer.Error.class, violation.getConstraintDescriptor().getPayload().iterator().next());
        */
    }

    /**
     * Person has a "Warning" payload on the ValidCustomer constraint.
     */
    @Test
    public void testPayload() {
        Person person = new Person("Bob");

        Set<ConstraintViolation<Customer>> violations = validate(person);
        assertEquals(1, violations.size());

        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{org.geofft.constraint.NameMustBeGeoff.message}", violation.getMessage());
        assertEquals("org.geofft.constraint.NameMustBeGeoff", violation.getConstraintDescriptor().getAnnotation().annotationType().getName());
        assertEquals(1, violation.getConstraintDescriptor().getPayload().size());
        assertEquals(Customer.Warning.class, violation.getConstraintDescriptor().getPayload().iterator().next());
    }

}
