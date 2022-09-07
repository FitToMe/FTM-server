package com.FitToMe.project.DTO.Comment;

import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmallGroupCommentDTO {

    private Long id;
    private String content;

    public SmallGroupCommentDTO(SmallGroupComment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
