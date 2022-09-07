package com.FitToMe.project.Service.ClassPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.DTO.CommunityPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.ClassPostRepository;
import com.FitToMe.project.Request.ClassPostRegisterRequest;
import com.FitToMe.project.Request.CommunityPostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassPostRegisterService {

    private final ClassPostRepository classPostRepository;

    public ClassPostDTO createPost(User user, ClassPostRegisterRequest postRegisterRequest) {
        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        return new ClassPostDTO(classPostRepository.save(new ClassPost(user, postRegisterRequest)));
    }
}
