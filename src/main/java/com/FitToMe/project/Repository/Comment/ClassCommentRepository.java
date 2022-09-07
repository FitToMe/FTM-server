package com.FitToMe.project.Repository.Comment;

import com.FitToMe.project.Entity.Comment.ClassComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassCommentRepository extends JpaRepository<ClassComment, Long> {

    List<ClassComment> findCommentsByUserId(Long userId);
    List<ClassComment> findCommentsByClassPostId(Long classPostId);
}
