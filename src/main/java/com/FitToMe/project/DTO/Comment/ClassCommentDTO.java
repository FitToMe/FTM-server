package com.FitToMe.project.DTO.Comment;

import com.FitToMe.project.Entity.Comment.ClassComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassCommentDTO {

    private Long id;
    private String content;

    public ClassCommentDTO(ClassComment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
