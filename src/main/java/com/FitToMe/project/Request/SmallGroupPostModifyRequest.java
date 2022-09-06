package com.FitToMe.project.Request;

import lombok.Getter;

@Getter
public class SmallGroupPostModifyRequest {

    private String title;
    private String content;
    private String imageURL;
    private Integer totalParticipant;
    private Integer cost;
}