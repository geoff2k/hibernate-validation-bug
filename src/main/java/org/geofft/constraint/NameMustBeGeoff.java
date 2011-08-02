package org.geofft.constraint;

import org.geofft.validator.NameMustBeGeoffValidator;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Constraint(validatedBy = NameMustBeGeoffValidator.class)
@Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface NameMustBeGeoff {

    String message() default "{org.geofft.constraint.NameMustBeGeoff.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
