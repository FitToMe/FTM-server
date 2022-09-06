package com.FitToMe.project.Request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SmallGroupPostRegisterRequest {

    @NotBlank(message = "제목은 필수 입력입니다")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다")
    private String content;

    private String imageURL;
    private Integer totalParticipant;
    private Integer cost;
}