package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Request.LoginRequest;
import com.FitToMe.project.Request.UserSignupRequest;
import com.FitToMe.project.Service.User.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;

    @Operation(summary = "회원가입")
    @PostMapping("signup")
    public ApiResult<UserDTO> signup(@Valid @RequestBody UserSignupRequest userSignupRequest) {
        return ApiResult.SUCCESS(userRegisterService.signup(userSignupRequest));
    }

    @Operation(summary = "로그인")
    @PostMapping("login")
    public ApiResult<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResult.SUCCESS(userLoginService.login(loginRequest));
    }
}