package com.FitToMe.project.Entity.Comment;

import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.CommunityCommentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityComment {

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
    @JoinColumn(name = "community_post_id")
    private CommunityPost communityPost;

    public static CommunityComment createComment(CommunityCommentRequest commentRequest, User user, CommunityPost communityPost) {
        return CommunityComment.builder()
                .commentRequest(commentRequest)
                .user(user)
                .communityPost(communityPost)
                .build();
    }

    @Builder
    private CommunityComment(CommunityCommentRequest commentRequest, User user, CommunityPost communityPost) {
        this.content = commentRequest.getContent();
        this.user = user;
        this.communityPost = communityPost;
    }
}
