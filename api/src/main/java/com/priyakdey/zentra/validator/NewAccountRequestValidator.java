package com.priyakdey.zentra.validator;

import com.priyakdey.zentra.model.request.NewAccountRequest;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.priyakdey.zentra.validator.NewAccountRequestValidator.ValidationResult.*;

/**
 * @author Priyak Dey
 */
public interface NewAccountRequestValidator
        extends Function<NewAccountRequest, NewAccountRequestValidator.ValidationResult> {

    Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    static NewAccountRequestValidator isValidName() {
        return newUser ->
                newUser.getName() != null && !newUser.getName().isEmpty()
                        ? SUCCESS
                        : INVALID_NAME;
    }

    static NewAccountRequestValidator isValidUsername() {
        return newUser ->
                newUser.getUsername() != null && !newUser.getUsername().isEmpty()
                        ? SUCCESS
                        : INVALID_USERNAME;
    }

    static NewAccountRequestValidator isValidPassword() {
        // TODO: check for password length and charset
        return newUser ->
                newUser.getPassword() != null
                        ? SUCCESS
                        : INVALID_USERNAME;
    }

    static NewAccountRequestValidator isValidEmail() {
        return newUser ->
                newUser.getEmail() != null && emailPattern.matcher(newUser.getEmail()).matches()
                        ? SUCCESS
                        : INVALID_EMAIL;
    }

    default NewAccountRequestValidator and(NewAccountRequestValidator next) {
        return newUser -> {
            ValidationResult result = this.apply(newUser);
            return result == SUCCESS ? next.apply(newUser) : result;
        };
    }

    enum ValidationResult {
        SUCCESS("Success"),
        INVALID_NAME("Invalid Name"),
        INVALID_USERNAME("Invalid Username"),
        INVALID_PASSWORD("Invalid Password"),
        INVALID_EMAIL("Invalid Email");

        private final String message;

        ValidationResult(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
