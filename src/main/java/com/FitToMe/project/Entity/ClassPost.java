package com.FitToMe.project.Entity;

import com.FitToMe.project.Request.ClassPostRegisterRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassPost extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imageURL;
    private Integer totalParticipant;
    private Integer participantNum;
    private Integer viewCnt;
    private Integer cost;
    private Boolean isRecruiting;

    public ClassPost(User user, ClassPostRegisterRequest postRegisterRequest) {
        this.user = user;
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
        this.totalParticipant = postRegisterRequest.getTotalParticipant();
        this.cost = postRegisterRequest.getCost();
        this.isRecruiting = true;
        this.participantNum = 0;
        this.viewCnt = 0;
    }
}