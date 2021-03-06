package com.FitToMe.project.Request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class LoginRequest {

    @NotBlank(message = "이메일은 필수 입력입니다")
    @Email(message = "이메일은 이메일 형식에 맞아야 됩니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력입니다")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자로 영문, 숫자, 특수문자를 적어도 하나 사용해야 됩니다")
    private String password;
}