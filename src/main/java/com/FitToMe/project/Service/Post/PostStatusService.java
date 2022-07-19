package com.FitToMe.project.Service.Post;

import com.FitToMe.project.DTO.PostDTO;
import com.FitToMe.project.Entity.Post;
import com.FitToMe.project.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)     //조회만 하는 경우 사용하는 속성
@Service
@RequiredArgsConstructor
public class PostStatusService {

    private final PostRepository postRepository;

    public PostDTO findOne(Long postId) throws NullPointerException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 id 게시글이 존재하지 않습니다"));
        return new PostDTO(post);
    }

    public PostDTO findOne(String postTitle) throws NullPointerException {
        Post post = postRepository.findByTitle(postTitle)
                .orElseThrow(() -> new NullPointerException("해당 title 게시글이 존재하지 않습니다"));
        return new PostDTO(post);
    }

    public List<PostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }
}
