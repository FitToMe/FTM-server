package com.FitToMe.project.DTO;

import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassPostDTO {

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

    public ClassPostDTO(ClassPost entity) {
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
