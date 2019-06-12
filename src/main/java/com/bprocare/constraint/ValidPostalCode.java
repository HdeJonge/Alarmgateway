package com.bprocare.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PostalCodeValidator.class)
@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPostalCode {

	  String message() default "";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};

	  boolean isUpdate() default true;
}
