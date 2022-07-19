package com.FitToMe.project.Service.Post;

import com.FitToMe.project.Entity.Post;
import com.FitToMe.project.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDeleteService {

    private final PostRepository postRepository;

    public boolean deletePost(Long postId) throws RuntimeException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        postRepository.deleteById(postId);
        return true;
    }
}
