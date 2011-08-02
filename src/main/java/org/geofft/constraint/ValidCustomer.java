package org.geofft.constraint;

import org.geofft.Customer;
import org.geofft.validator.ValidCustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameMustBeGeoff(payload = Customer.Error.class) // TODO : why does this get lost?
@Documented
@Constraint(validatedBy = ValidCustomerValidator.class)
@Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidCustomer {

    String message() default "{poscore.model.validation.constraint.ValidCustomer.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
