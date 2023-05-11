package com.kakaoCoffee.common;

import com.kakaoCoffee.common.customEnum.ErrorType;
import com.kakaoCoffee.common.dto.ErrorResponseDto;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.RUNTIME_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.ENTITY_NOT_FOUND_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(ValidationException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.VALIDATION_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponseDto> handleJwtException(JwtException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.JWT_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.ILLEGAL_ARGUMENT_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.ACCESS_DENIED_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(BadCredentialsException e) {
        log.error(e.toString() + " occurred: {}", e.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.BAD_CREDENTIALS_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseDto);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
//        BindingResult bindingResult = e.getBindingResult();
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError error : bindingResult.getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//        ErrorResponseDto errorResponseDto = ErrorResponseDto.of(ErrorType.VALIDATION_EXCEPTION, errors.toString());
//        ApiResponse<ErrorResponseDto> errorResponseData = ApiResponse.failOf(HttpStatus.BAD_REQUEST, errorResponseDto);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseData);
//    }
}
