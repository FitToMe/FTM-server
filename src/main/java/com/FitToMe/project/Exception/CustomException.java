package com.FitToMe.project.Exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final CustomError customError;

    public CustomException(CustomError customError) {
        super(customError.name());
        this.customError = customError;
    }
}