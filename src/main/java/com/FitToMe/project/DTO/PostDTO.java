package com.FitToMe.project.DTO;

import com.FitToMe.project.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String imageURL;

    //// Q. Date의 경우 자동업데이트되는데, DTO의 변수로 작성되어야하는게 맞을까요?
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private String authorNickname;

    // entity -> dto로 변환
    public PostDTO(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.imageURL = entity.getImageURL();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.authorNickname = entity.getUser().getNickname();
    }
}
