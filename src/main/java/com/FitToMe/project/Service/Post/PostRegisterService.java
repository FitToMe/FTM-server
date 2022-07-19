package com.FitToMe.project.Service.Post;

import com.FitToMe.project.DTO.PostDTO;
import com.FitToMe.project.Entity.Post;
import com.FitToMe.project.Repository.PostRepository;
import com.FitToMe.project.Request.PostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostRegisterService {

    private final PostRepository postRepository;

    public PostDTO createPost(PostRegisterRequest postRegisterRequest) {
        return new PostDTO(postRepository.save(new Post(postRegisterRequest)));
    }
}
