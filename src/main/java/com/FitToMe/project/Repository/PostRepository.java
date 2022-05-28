package com.FitToMe.project.Repository;

import com.FitToMe.project.Entity.Post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

//    Optional<PostEntity> findByTitle(String postTitle);
    Optional<PostEntity> findAllTitle(String postTitle); //check
}