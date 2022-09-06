package com.FitToMe.project.Entity;

import com.FitToMe.project.Request.PostRegisterRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmallGroupPost extends TimeBase {

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

    public SmallGroupPost(User user, PostRegisterRequest postRegisterRequest) {
        this.user = user;
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
    }
}