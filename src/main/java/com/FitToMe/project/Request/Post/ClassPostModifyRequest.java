package com.FitToMe.project.Request.Post;

import com.FitToMe.project.Entity.Category;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ClassPostModifyRequest {

    private String title;
    private String content;
    private String imageURL;
    private Integer totalParticipant;
    private Integer cost;
    private Category category;
}