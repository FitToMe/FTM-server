package com.FitToMe.project.Repository.Post;

import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.ClassPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassPostRepository extends JpaRepository<ClassPost, Long> {

    Optional<ClassPost> findByTitle(String postTitle);

    Page<ClassPost> findAllByCategory(Category category, Pageable pageable);
}