package com.FitToMe.project.Service.Post;

import com.FitToMe.project.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDeleteService {

    private final PostRepository postRepository;

    public boolean deletePost(Long postId){
        postRepository.deleteById(postId);
        return true;
    }
}
