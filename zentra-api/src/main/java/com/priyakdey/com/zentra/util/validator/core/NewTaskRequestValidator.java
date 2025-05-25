package com.priyakdey.com.zentra.util.validator.core;

import com.priyakdey.com.zentra.model.request.NewTaskRequest;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.function.Function;

import static com.priyakdey.com.zentra.util.validator.core.ValidationResult.SUCCESS;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_DATE;
import static com.priyakdey.com.zentra.util.validator.core.ValidationResultType.INVALID_TITLE;

/**
 * @author Priyak Dey
 */
public interface NewTaskRequestValidator extends Function<NewTaskRequest, ValidationResult> {

    static NewTaskRequestValidator isValidTitle() {
        return request -> {
            String title = request.getTitle();
            if (title == null || title.isBlank()) {
                return new ValidationResult(INVALID_TITLE, "Title is required");
            }

            return SUCCESS;
        };
    }

    static NewTaskRequestValidator isValidCompletionDate() {
        return request -> {
            ZonedDateTime dateTime = request.getTentativeCompletionDate();
            if (dateTime == null) {
                return SUCCESS;
            }

            LocalDate today = LocalDate.now(Clock.systemUTC());
            LocalDate completionDate = dateTime.toLocalDate();
            if (completionDate.isBefore(today)) {
                return new ValidationResult(INVALID_DATE, "Completion date cannot be in the past");
            }

            return SUCCESS;
        };
    }

    default NewTaskRequestValidator and(NewTaskRequestValidator next) {
        return request -> {
            ValidationResult result = this.apply(request);
            if (!result.isSuccess()) {
                return result;
            }

            return next.apply(request);
        };
    }

}
