package com.FitToMe.project.Request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ClassPostModifyRequest {

    private String title;
    private String content;
    private String imageURL;
    private Integer totalParticipant;
    private Integer cost;
}