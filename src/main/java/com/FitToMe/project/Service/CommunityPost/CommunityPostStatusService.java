package com.FitToMe.project.Service.CommunityPost;

import com.FitToMe.project.DTO.CommunityPostDTO;
import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.CommunityPostRepository;
import lombok.RequiredArgsConstructor;
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
}
