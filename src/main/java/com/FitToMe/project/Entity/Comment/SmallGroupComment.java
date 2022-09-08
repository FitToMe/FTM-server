package com.FitToMe.project.Entity.Comment;

import com.FitToMe.project.Entity.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.SmallGroupCommentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmallGroupComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smallGroup_post_id")
    private SmallGroupPost post;

    public static SmallGroupComment createComment(SmallGroupCommentRequest commentRequest, User user, SmallGroupPost post) {
        return SmallGroupComment.builder()
                .commentRequest(commentRequest)
                .user(user)
                .post(post)
                .build();
    }

    @Builder
    private SmallGroupComment(SmallGroupCommentRequest commentRequest, User user, SmallGroupPost post) {
        this.content = commentRequest.getContent();
        this.user = user;
        this.post = post;
    }
}
