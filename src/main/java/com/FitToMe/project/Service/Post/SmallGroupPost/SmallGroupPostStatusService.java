package com.FitToMe.project.Service.Post.SmallGroupPost;

import com.FitToMe.project.DTO.Post.SmallGroupPostDTO;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)     //조회만 하는 경우 사용하는 속성
@Service
@RequiredArgsConstructor
public class SmallGroupPostStatusService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public SmallGroupPostDTO findOne(Long postId) {
        SmallGroupPost post = smallGroupPostRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 id 게시글이 존재하지 않습니다"));
        return new SmallGroupPostDTO(post);
    }

    public SmallGroupPostDTO findOne(String postTitle) {
        SmallGroupPost post = smallGroupPostRepository.findByTitle(postTitle)
                .orElseThrow(() -> new NullPointerException("해당 title 게시글이 존재하지 않습니다"));
        return new SmallGroupPostDTO(post);
    }

    public List<SmallGroupPostDTO> findAll() {
        List<SmallGroupPost> posts = smallGroupPostRepository.findAll();
        return posts.stream().map(SmallGroupPostDTO::new).collect(Collectors.toList());
    }
}
