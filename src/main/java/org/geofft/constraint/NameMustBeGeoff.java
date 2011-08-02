package org.geofft.constraint;

import org.geofft.validator.NameMustBeGeoffValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NameMustBeGeoffValidator.class)
@Target({ TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface NameMustBeGeoff {

    String message() default "{org.geofft.constraint.NameMustBeGeoff.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
