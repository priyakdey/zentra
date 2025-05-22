package com.priyakdey.com.zentra.util.validator;

import com.priyakdey.com.zentra.model.request.AuthRequest;
import com.priyakdey.com.zentra.util.validator.core.ValidationResult;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.priyakdey.com.zentra.util.validator.core.ValidationResult.SUCCESS;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_EMAIL;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_PASSWORD;

/**
 * @author Priyak Dey
 */
public interface AuthRequestValidator extends Function<AuthRequest, ValidationResult> {


    // https://github.com/colinhacks/zod/blob/e62341b1aaf720709ee5f31785db25d5c0491659/src/types.ts#L648
    Pattern EMAIL_PATTERN = Pattern.compile("^(?!\\.)(?!.*\\.\\.)([A-Z0-9_'+\\-\\.]*[A-Z0-9_+\\-])@([A-Z0-9][A-Z0-9\\-]*\\.)+[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE);

    // https://datatracker.ietf.org/doc/html/rfc5321#section-4.5.3.1
    int EMAIL_MAX_LENGTH = 254;

    static AuthRequestValidator isValidEmail() {
        return authRequest -> {
            String email = authRequest.getEmail();
            if (email == null || email.isBlank()) {
                return new ValidationResult(INVALID_EMAIL, "Email is required");
            } else if (email.length() > EMAIL_MAX_LENGTH) {
                return new ValidationResult(INVALID_EMAIL, "Email address must not exceed 254 characters");
            } else if (!EMAIL_PATTERN.matcher(email).matches()) {
                return new ValidationResult(INVALID_EMAIL, "Email address is not valid");
            }

            return SUCCESS;

        };
    }

    static AuthRequestValidator isValidPassword() {
        return authRequest -> {
            char[] data = authRequest.getPassword().data();
            if (data == null || data.length < 8 || data.length > 25) {
                return new ValidationResult(INVALID_PASSWORD, "Password must be between 8 and 25 characters");
            }

            return SUCCESS;
        };
    }

    default AuthRequestValidator and(AuthRequestValidator next) {
        return authRequest -> {
            ValidationResult result = this.apply(authRequest);
            if (result.isSuccess()) {
                return next.apply(authRequest);
            }
            return result;
        };
    }
}
