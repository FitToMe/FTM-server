package com.FitToMe.project.Request;

import com.FitToMe.project.Entity.Category;
import lombok.Getter;

@Getter
public class CommunityPostModifyRequest {

    private String title;
    private String content;
    private String imageURL;
    private Category category;
}