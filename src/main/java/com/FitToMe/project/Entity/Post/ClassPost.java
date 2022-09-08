package com.FitToMe.project.Entity.Post;

import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.TimeBase;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Post.ClassPostRegisterRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
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

    @Enumerated(EnumType.STRING)
    private Category category;

    public ClassPost(User user, ClassPostRegisterRequest postRegisterRequest) {
        this.user = user;
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
        this.totalParticipant = postRegisterRequest.getTotalParticipant();
        this.cost = postRegisterRequest.getCost();
        this.category = postRegisterRequest.getCategory();

        this.isRecruiting = true;
        this.participantNum = 0;
        this.viewCnt = 0;
    }
}