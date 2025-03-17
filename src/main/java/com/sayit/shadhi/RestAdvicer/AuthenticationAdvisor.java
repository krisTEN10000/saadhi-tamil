package com.sayit.shadhi.RestAdvicer;

import com.fasterxml.jackson.core.JsonParseException;
import com.sayit.shadhi.Exceptions.OtpNotValidException;
import com.sayit.shadhi.Exceptions.VerificationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationAdvisor {

    @ExceptionHandler(VerificationFailedException.class)
    public ResponseEntity<String> sendMailNotVerifiedException(VerificationFailedException ve){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ve.getMessage());

    }
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> sendJsonProcessingFailed(JsonParseException jsonParseException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonParseException.getMessage());
    }

    @ExceptionHandler(OtpNotValidException.class)
    public ResponseEntity<String> sendOtpNotValidException(OtpNotValidException otpNotValidException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(otpNotValidException.getMessage());
    }
}
