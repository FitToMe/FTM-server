package com.FitToMe.project.Entity.Comment;

import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.TimeBase;
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
public class SmallGroupComment extends TimeBase {

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
    private SmallGroupPost smallGroupPost;

    public static SmallGroupComment createComment(SmallGroupCommentRequest commentRequest, User user, SmallGroupPost smallGroupPost) {
        return SmallGroupComment.builder()
                .commentRequest(commentRequest)
                .user(user)
                .smallGroupPost(smallGroupPost)
                .build();
    }

    @Builder
    private SmallGroupComment(SmallGroupCommentRequest commentRequest, User user, SmallGroupPost smallGroupPost) {
        this.content = commentRequest.getContent();
        this.user = user;
        this.smallGroupPost = smallGroupPost;
    }
}
