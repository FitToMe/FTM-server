package com.FitToMe.project.Exception;

import com.FitToMe.project.ApiResult.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> CustomExceptionHandler(CustomException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ApiResult.ERROR(exception));
    }
}
