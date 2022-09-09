package com.FitToMe.project.Service.Post.SmallGroupPost;

import com.FitToMe.project.DTO.Post.SmallGroupPostDTO;
import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new SmallGroupPostDTO(post);
    }

    public SmallGroupPostDTO findOne(String postTitle) {
        SmallGroupPost post = smallGroupPostRepository.findByTitle(postTitle)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new SmallGroupPostDTO(post);
    }

    public List<SmallGroupPostDTO> findAll() {
        List<SmallGroupPost> posts = smallGroupPostRepository.findAll();
        return posts.stream().map(SmallGroupPostDTO::new).collect(Collectors.toList());
    }

    public Page<SmallGroupPostDTO> findAllByCategoryAndPaging(Category category, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("regDate").descending());
        Page<SmallGroupPost> posts = smallGroupPostRepository.findAllByCategory(category, pageable);
        return posts.map(SmallGroupPostDTO::new);
    }
}
