package com.priyakdey.planner.exception.handler;

import com.priyakdey.planner.exception.BadRequestException;
import com.priyakdey.planner.exception.EmailExistsException;
import com.priyakdey.planner.exception.UsernameExistsException;
import com.priyakdey.planner.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Priyak Dey
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class, UsernameExistsException.class,
            EmailExistsException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
