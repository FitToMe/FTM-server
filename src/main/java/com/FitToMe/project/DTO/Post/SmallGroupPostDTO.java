package com.FitToMe.project.DTO.Post;

import com.FitToMe.project.Entity.Post.SmallGroupPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SmallGroupPostDTO {

    private Long id;
    private String title;
    private String content;
    private String imageURL;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String authorNickname;
    private Integer totalParticipant;
    private Integer participantNum;
    private Integer viewCnt;
    private Integer cost;
    private Boolean isRecruiting;

    public SmallGroupPostDTO(SmallGroupPost entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.imageURL = entity.getImageURL();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.authorNickname = entity.getUser().getNickname();
        this.totalParticipant = entity.getTotalParticipant();
        this.participantNum = entity.getParticipantNum();
        this.viewCnt = entity.getViewCnt();
        this.cost = entity.getCost();
        this.isRecruiting = entity.getIsRecruiting();
    }
}
