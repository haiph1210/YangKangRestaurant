package com.haiph.salesservice.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2(topic = "ErrorLogger")
@RestControllerAdvice
public class BaseErrorHandlers {
    @ExceptionHandler(value = ValidatorException.class)
    public ResponseEntity<String> handleException(ValidatorException exception) {
        log.error(exception.getMsg(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMsg());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBody handleValidationException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<FieldError> fieldErrors = exception.getBindingResult().getAllErrors().stream().map(FieldError.class::cast).collect(Collectors.toList());
        Response response = Response.PARAM_INVALID;
        if (fieldErrors.stream().map(s -> s.unwrap(ConstraintViolation.class))
                .anyMatch(e -> e.getConstraintDescriptor().getAnnotation() instanceof NotBlank)) {
//            response = Response.MISSING_PARAM;
        }
        return new ResponseBody(response,
                fieldErrors.stream().collect(Collectors.toMap(
                        FieldError::getField,
                        error -> String.valueOf(error.getDefaultMessage()), (p, q) -> p)
                ));
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CommonException.class)
    public ResponseBody hanldeCommonException(CommonException exception) {
        log.error("Common Exception Error: ", exception);
        return new ResponseBody(exception.getResponse(), exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseBody handleAuthorization(AccessDeniedException exception) {
        log.error("Author Error: " + exception);
        return new ResponseBody(Response.ACCESS_DENIED.getResponseCode(), Response.ACCESS_DENIED.getResponseMessage(), exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public ResponseBody handleAuthorization(AuthorizationException exception) {
        log.error("Author Error: " + exception);
        return new ResponseBody(Response.ACCESS_DENIED.getResponseCode(), Response.ACCESS_DENIED.getResponseMessage(), exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseBody handleEntityNotFound(EntityExistsException entityExistsException) {
        log.error("Entity Not Found Error: " + entityExistsException);
        return new ResponseBody(Response.OBJECT_NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoResultException.class)
    public ResponseBody handleNoResult(NoResultException exception) {
        log.error("No Result Error: " + exception);
        return new ResponseBody(Response.DATA_NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseBody handleException(Exception exception) {
        log.error("Exception: ", exception);
        return new ResponseBody(Response.SYSTEM_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseBody handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Exception Error: ", e);
        return new ResponseBody(Response.PARAM_NOT_VALID, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseBody handleIllegalArgumentException(HttpMessageNotReadableException e) {
        log.error("Http message Error: ", e);
        Map<String, String> mapErrMess = ((MismatchedInputException) e.getCause()).getPath().stream()
                .collect(Collectors.toMap(JsonMappingException.Reference::getFieldName, s -> "Không đúng định dạng"));
        return new ResponseBody(Response.PARAM_INVALID, mapErrMess);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseBody hanldleUserNotFoundException(UserNotFoundException exception) {
        log.error("User not found error : " + exception);
        Map<String,String> map = new HashMap<>();
        map.put("Error",exception.getMsg());
        return new ResponseBody(Response.OBJECT_NOT_FOUND,map);
    }
}
