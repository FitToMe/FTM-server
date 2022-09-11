package com.FitToMe.project.DTO.Comment;

import com.FitToMe.project.Entity.Comment.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCommentDTO {

    private Long id;
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private String authorNickname;

    public CommunityCommentDTO(CommunityComment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.authorNickname = entity.getUser().getNickname();
    }
}
