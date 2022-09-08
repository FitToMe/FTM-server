package com.FitToMe.project.Entity.Comment;

import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.ClassCommentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassComment {

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
    @JoinColumn(name = "class_post_id")
    private ClassPost post;

    public static ClassComment createComment(ClassCommentRequest commentRequest, User user, ClassPost post) {
        return ClassComment.builder()
                .commentRequest(commentRequest)
                .user(user)
                .post(post)
                .build();
    }

    @Builder
    private ClassComment(ClassCommentRequest commentRequest, User user, ClassPost post) {
        this.content = commentRequest.getContent();
        this.user = user;
        this.post = post;
    }
}
