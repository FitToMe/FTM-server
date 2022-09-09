package com.FitToMe.project.Service.Post.ClassPost;

import com.FitToMe.project.DTO.Post.ClassPostDTO;
import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.ClassPostRepository;
import com.FitToMe.project.Request.Post.ClassPostRegisterRequest;
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
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        return new ClassPostDTO(classPostRepository.save(new ClassPost(user, postRegisterRequest)));
    }
}
