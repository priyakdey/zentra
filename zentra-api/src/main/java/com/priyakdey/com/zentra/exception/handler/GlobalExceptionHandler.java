package com.priyakdey.com.zentra.exception.handler;

import com.priyakdey.com.zentra.exception.AccountNotFoundException;
import com.priyakdey.com.zentra.exception.EmailExistsException;
import com.priyakdey.com.zentra.exception.InvalidCredentialsException;
import com.priyakdey.com.zentra.exception.InvalidRequestException;
import com.priyakdey.com.zentra.model.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Input", ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExistsException() {
        ErrorResponse errorResponse = new ErrorResponse("Email already in use",
                "An account with this email already exists. Try logging in or use a different email.");
        return ResponseEntity.status(CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleAccountDoesNotExistException() {
        ErrorResponse errorResponse = new ErrorResponse("Invalid credentials",
                "The credentials you entered are incorrect. Please try again.");
        return ResponseEntity.status(UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> AccountNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("ERROR: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("Something went wrong",
                "We're experiencing an issue right now. Please try again in a moment.");
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
