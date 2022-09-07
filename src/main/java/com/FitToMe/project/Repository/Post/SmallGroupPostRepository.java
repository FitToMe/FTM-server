package com.FitToMe.project.Repository.Post;

import com.FitToMe.project.Entity.Post.SmallGroupPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmallGroupPostRepository extends JpaRepository<SmallGroupPost, Long> {
    Optional<SmallGroupPost> findByTitle(String postTitle);
}