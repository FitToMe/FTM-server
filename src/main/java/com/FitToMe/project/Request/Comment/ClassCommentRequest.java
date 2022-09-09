package com.FitToMe.project.Request.Comment;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class ClassCommentRequest {

    @NotBlank(message = "내용은 필수 입력입니다")
    private String content;
}
