package com.FitToMe.project.Repository;

import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.SmallGroupPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmallGroupPostRepository extends JpaRepository<SmallGroupPost, Long> {
    Optional<SmallGroupPost> findByTitle(String postTitle);
}