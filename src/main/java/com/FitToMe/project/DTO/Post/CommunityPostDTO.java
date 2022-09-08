package com.FitToMe.project.DTO.Post;

import com.FitToMe.project.Entity.Post.CommunityPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityPostDTO {

    private Long id;
    private String title;
    private String content;
    private String imageURL;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String authorNickname;
    private Integer viewCnt;

    public CommunityPostDTO(CommunityPost entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.imageURL = entity.getImageURL();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.authorNickname = entity.getUser().getNickname();
        this.viewCnt = entity.getViewCnt();
    }
}
