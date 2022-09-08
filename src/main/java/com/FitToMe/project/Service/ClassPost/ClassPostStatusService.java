package com.FitToMe.project.Service.ClassPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.ClassPostRepository;
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
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new ClassPostDTO(post);
    }

    public ClassPostDTO findOne(String postTitle) {
        ClassPost post = classPostRepository.findByTitle(postTitle)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new ClassPostDTO(post);
    }

    public List<ClassPostDTO> findAll() {
        List<ClassPost> posts = classPostRepository.findAll();
        return posts.stream().map(ClassPostDTO::new).collect(Collectors.toList());
    }
}
