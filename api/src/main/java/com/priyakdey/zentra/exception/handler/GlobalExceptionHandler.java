package com.priyakdey.zentra.exception.handler;

import com.priyakdey.planner.exception.*;
import com.priyakdey.zentra.exception.*;
import com.priyakdey.zentra.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Priyak Dey
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class, UsernameExistsException.class,
            EmailExistsException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {
        return generateResponse(BAD_REQUEST, ex);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return generateResponse(NOT_FOUND, ex);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException ex) {
        return generateResponse(UNAUTHORIZED, ex);
    }

    @ExceptionHandler({AccountNotActivatedException.class})
    public ResponseEntity<ErrorResponse> handleAccountNotActivatedException(
            AccountNotActivatedException ex) {
        return generateResponse(FORBIDDEN, ex);
    }

    private ResponseEntity<ErrorResponse> generateResponse(HttpStatus status, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
