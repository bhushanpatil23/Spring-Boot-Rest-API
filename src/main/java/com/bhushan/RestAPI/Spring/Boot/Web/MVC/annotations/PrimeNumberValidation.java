package com.bhushan.RestAPI.Spring.Boot.Web.MVC.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrimeNumberValidator.class)
public @interface PrimeNumberValidation {


    String message() default " Number must be a prime number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
