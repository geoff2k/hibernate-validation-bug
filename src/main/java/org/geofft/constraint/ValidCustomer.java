package org.geofft.constraint;

import org.geofft.Customer;
import org.geofft.validator.ValidCustomerValidator;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Note the @NameMustBeGeoff constraint and its payload.
 * <p/>
 * The issue is that this payload is not surfaced.
 */
@NameMustBeGeoff(payload = Customer.Error.class)
@Documented
@Constraint(validatedBy = ValidCustomerValidator.class)
@Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidCustomer {

    String message() default "{poscore.model.validation.constraint.ValidCustomer.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
