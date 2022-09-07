package com.FitToMe.project.DTO.Comment;

import com.FitToMe.project.Entity.Comment.CommunityComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityCommentDTO {

    private Long id;
    private String content;

    public CommunityCommentDTO(CommunityComment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
