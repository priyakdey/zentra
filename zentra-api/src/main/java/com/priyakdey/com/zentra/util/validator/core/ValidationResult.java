package com.priyakdey.com.zentra.util.validator.core;

/**
 * @author Priyak Dey
 */
public record ValidationResult(ValidationResultType validationResultType, String message) {

    public static ValidationResult SUCCESS = new ValidationResult(ValidationResultType.VALID, null);

    public boolean isSuccess() {
        return validationResultType == ValidationResultType.VALID;
    }

}
