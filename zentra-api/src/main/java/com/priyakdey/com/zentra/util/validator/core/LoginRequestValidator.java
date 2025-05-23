package com.priyakdey.com.zentra.util.validator.core;

import com.priyakdey.com.zentra.model.request.LoginRequest;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.priyakdey.com.zentra.util.validator.core.ValidationResult.SUCCESS;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_EMAIL;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_PASSWORD;

/**
 * @author Priyak Dey
 */
public interface LoginRequestValidator extends Function<LoginRequest, ValidationResult> {

    // https://github.com/colinhacks/zod/blob/e62341b1aaf720709ee5f31785db25d5c0491659/src/types.ts#L648
    Pattern EMAIL_PATTERN = Pattern.compile("^(?!\\.)(?!.*\\.\\.)([A-Z0-9_'+\\-\\.]*[A-Z0-9_+\\-])@([A-Z0-9][A-Z0-9\\-]*\\.)+[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE);

    // https://datatracker.ietf.org/doc/html/rfc5321#section-4.5.3.1
    int EMAIL_MAX_LENGTH = 254;


    static LoginRequestValidator isValidEmail() {
        return request -> {
            String email = request.getEmail();
            if (email == null || email.isBlank()) {
                return new ValidationResult(INVALID_EMAIL, "Email is required");
            } else if (email.length() > EMAIL_MAX_LENGTH) {
                return new ValidationResult(INVALID_EMAIL,
                        "Email address must not exceed 254 characters");
            } else if (!EMAIL_PATTERN.matcher(email).matches()) {
                return new ValidationResult(INVALID_EMAIL,
                        "Please enter a valid email address");
            }

            return SUCCESS;
        };
    }

    static LoginRequestValidator isValidPassword() {
        return request -> {
            char[] data = request.getPassword().data();
            if (data == null || data.length < 8 || data.length > 25) {
                return new ValidationResult(INVALID_PASSWORD,
                        "Password must be between 8 and 25 characters");
            }

            return SUCCESS;
        };
    }

    default LoginRequestValidator and(LoginRequestValidator next) {
        return request -> {
            ValidationResult result = this.apply(request);
            if (result.isSuccess()) {
                return next.apply(request);
            }
            return result;
        };
    }

}
