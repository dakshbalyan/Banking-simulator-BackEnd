package com.personal.atmSimulatorBackEnd.responseObjects;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.customExceptions.InvalidAmountException;
import com.personal.atmSimulatorBackEnd.customExceptions.InvalidInputException;
import com.personal.atmSimulatorBackEnd.customExceptions.WrongCredentialsException;
import com.personal.atmSimulatorBackEnd.entities.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class ResponseObjectExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountDoesNotExistException.class)    // Tells the spring that this is the method to be called when
    public ResponseEntity<ErrorMessage> AccountNotFoundException(        // ResponseEntity is of ErrorMessage type
         AccountDoesNotExistException exception
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND, exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(InvalidInputException.class)    // Tells the spring that this is the method to be called when
    public ResponseEntity<ErrorMessage> invalidInputException(        // ResponseEntity is of ErrorMessage type
         InvalidInputException exception
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND, exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(InvalidAmountException.class)    // Tells the spring that this is the method to be called when
    public ResponseEntity<ErrorMessage> invalidAmountException(        // ResponseEntity is of ErrorMessage type
           InvalidAmountException exception
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND, exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(WrongCredentialsException.class)    // Tells the spring that this is the method to be called when
    public ResponseEntity<ErrorMessage> wrongCredentialsException(        // ResponseEntity is of ErrorMessage type
         WrongCredentialsException exception
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND, exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}
