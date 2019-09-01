package tech.kibrit.travelagency.annotation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String regexPassword="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#%&*$])(?=\\S+$).{8,15}";
        return ((password != null && Pattern.compile(regexPassword).matcher(password).matches()));

}

}

