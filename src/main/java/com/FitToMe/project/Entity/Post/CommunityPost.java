package com.FitToMe.project.Entity.Post;

import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.TimeBase;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Post.CommunityPostRegisterRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPost extends TimeBase {

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
    private Integer viewCnt;

    @Enumerated(EnumType.STRING)
    private Category category;

    public CommunityPost(User user, CommunityPostRegisterRequest postRegisterRequest) {
        this.user = user;
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
        this.category = postRegisterRequest.getCategory();

        this.viewCnt = 0;
    }
}