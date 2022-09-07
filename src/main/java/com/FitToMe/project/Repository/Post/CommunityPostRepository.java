package com.FitToMe.project.Repository.Post;

import com.FitToMe.project.Entity.Post.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    Optional<CommunityPost> findByTitle(String postTitle);
}