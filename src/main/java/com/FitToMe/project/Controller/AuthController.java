package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.LoginRequest;
import com.FitToMe.project.Request.UserModifyRequest;
import com.FitToMe.project.Request.UserSignupRequest;
import com.FitToMe.project.Service.User.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "auth", description = "유저 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;
    private final UserModifyService userModifyService;
    private final UserDeleteService userDeleteService;

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

    @Operation(summary = "회원 정보 조회")
    @GetMapping
    public ApiResult<UserDTO> getUserInfo(@AuthUser User user) {
        return ApiResult.SUCCESS(new UserDTO(user));
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    public ApiResult<UserDTO> modifyUserInfo(@AuthUser User user, @Valid @RequestBody UserModifyRequest userModifyRequest) {
        return ApiResult.SUCCESS(userModifyService.modifyUser(user, userModifyRequest));
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ApiResult<Boolean> deleteUser(@AuthUser User user) {
        return ApiResult.SUCCESS(userDeleteService.delete(user));
    }
}