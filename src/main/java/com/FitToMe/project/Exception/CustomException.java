package com.FitToMe.project.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final CustomError customError;

    public CustomException(CustomError customError) {
        super(customError.name());
        this.customError = customError;
    }

    public HttpStatus getHttpStatus() {
        return customError.getHttpStatus();
    }
}