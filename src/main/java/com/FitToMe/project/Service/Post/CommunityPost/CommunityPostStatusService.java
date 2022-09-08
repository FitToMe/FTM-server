package com.FitToMe.project.Service.Post.CommunityPost;

import com.FitToMe.project.DTO.Post.CommunityPostDTO;
import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
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
public class CommunityPostStatusService {

    private final CommunityPostRepository communityPostRepository;

    public CommunityPostDTO findOne(Long postId) {
        CommunityPost post = communityPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new CommunityPostDTO(post);
    }

    public CommunityPostDTO findOne(String postTitle) {
        CommunityPost post = communityPostRepository.findByTitle(postTitle)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));
        return new CommunityPostDTO(post);
    }

    public List<CommunityPostDTO> findAll() {
        List<CommunityPost> posts = communityPostRepository.findAll();
        return posts.stream().map(CommunityPostDTO::new).collect(Collectors.toList());
    }

    public Page<CommunityPostDTO> findAllByCategoryAndPaging(Category category, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("regDate").descending());
        Page<CommunityPost> posts = communityPostRepository.findAllByCategory(category, pageable);
        return posts.map(CommunityPostDTO::new);
    }
}
