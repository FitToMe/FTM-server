package com.FitToMe.project.Service.Post.ClassPost;

import com.FitToMe.project.DTO.Post.ClassPostDTO;
import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Repository.Post.ClassPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)     //조회만 하는 경우 사용하는 속성
@Service
@RequiredArgsConstructor
public class ClassPostStatusService {

    private final ClassPostRepository classPostRepository;

    public ClassPostDTO findOne(Long postId) {
        ClassPost post = classPostRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 id 게시글이 존재하지 않습니다"));
        return new ClassPostDTO(post);
    }

    public ClassPostDTO findOne(String postTitle) {
        ClassPost post = classPostRepository.findByTitle(postTitle)
                .orElseThrow(() -> new NullPointerException("해당 title 게시글이 존재하지 않습니다"));
        return new ClassPostDTO(post);
    }

    public List<ClassPostDTO> findAll() {
        List<ClassPost> posts = classPostRepository.findAll();
        return posts.stream().map(ClassPostDTO::new).collect(Collectors.toList());
    }
}
