package com.bhushan.RestAPI.Spring.Boot.Web.MVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Long> {
    @Override
    public boolean isValid(Long number, ConstraintValidatorContext context) {

        if (number == null || number <= 1) {
            return buildViolation(context);
        }

        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) {
            return buildViolation(context);
        }

        long sqrt = (long) Math.sqrt(number);
        for (long i = 5; i <= sqrt; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return buildViolation(context);
            }
        }

        return true;
    }

    private boolean buildViolation(ConstraintValidatorContext context) {
        // Disable the default message (which includes path)
        context.disableDefaultConstraintViolation();

        // Provide custom, clean message
        context.buildConstraintViolationWithTemplate("Please provide a prime number in the path variable")
                .addConstraintViolation();
        return false;
    }
}
