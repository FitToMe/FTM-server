package com.FitToMe.project.ApiResult;

import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private final String message;
    private final int status;

    public ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();   //Return the integer value of this status code
    }

    public ApiError(CustomException customException) {
        CustomError customError = customException.getCustomError();
        this.message = customError.getMessage();
        this.status = customError.getHttpStatus().value();
    }
}
