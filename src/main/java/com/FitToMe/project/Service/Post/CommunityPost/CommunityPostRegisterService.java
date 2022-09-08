package com.FitToMe.project.Service.Post.CommunityPost;

import com.FitToMe.project.DTO.Post.CommunityPostDTO;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import com.FitToMe.project.Request.Post.CommunityPostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostRegisterService {

    private final CommunityPostRepository communityPostRepository;

    public CommunityPostDTO createPost(User user, CommunityPostRegisterRequest postRegisterRequest) {
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        return new CommunityPostDTO(communityPostRepository.save(new CommunityPost(user, postRegisterRequest)));
    }
}
