package pl.edu.agh.kt.validation;

import pl.edu.agh.kt.repository.RepositoryUtils;
import pl.edu.agh.kt.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidateUsername, String> {

    private UserRepository userRepository;

    @Override
    public void initialize(ValidateUsername constraintAnnotation) {
        userRepository = RepositoryUtils.getUserRepository();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username != null && username.length() > 6;
        //return userRepository.findByUsername(username) == null;
    }
}
