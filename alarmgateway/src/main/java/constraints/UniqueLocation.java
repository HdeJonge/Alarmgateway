package constraints;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLocationValidator.class)
public @interface UniqueLocation {

	String message() default "combination of postal code and street number must be unique";

	String postalCode();

	String streetNumber();
	
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
