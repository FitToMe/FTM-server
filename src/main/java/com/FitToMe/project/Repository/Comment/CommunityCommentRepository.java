package com.FitToMe.project.Repository.Comment;

import com.FitToMe.project.Entity.Comment.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

    List<CommunityComment> findCommentsByUserId(Long userId);

    List<CommunityComment> findCommentsByCommunityPostId(Long communityPostId);
}
