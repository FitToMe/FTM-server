package com.FitToMe.project.Repository.Comment;

import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmallGroupCommentRepository extends JpaRepository<SmallGroupComment, Long> {

    List<SmallGroupComment> findCommentsByUserId(Long userId);

    List<SmallGroupComment> findCommentsBySmallGroupPostId(Long smallGroupPostId);
}
