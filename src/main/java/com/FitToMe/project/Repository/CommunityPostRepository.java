package com.FitToMe.project.Repository;

import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.SmallGroupPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    Optional<CommunityPost> findByTitle(String postTitle);
}