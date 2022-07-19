package com.FitToMe.project.Request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostModifyRequest {

    private String title;
    private String content;
    private String imageURL;
}
