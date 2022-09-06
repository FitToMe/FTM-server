package com.FitToMe.project.Entity;

import com.FitToMe.project.Request.CommunityPostRegisterRequest;
import lombok.*;
import org.springframework.stereotype.Service;

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

    public CommunityPost(User user, CommunityPostRegisterRequest postRegisterRequest) {
        this.user = user;
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
        this.viewCnt = 0;
    }
}