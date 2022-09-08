package com.FitToMe.project.Service.ClassPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.ClassPostRepository;
import com.FitToMe.project.Request.ClassPostRegisterRequest;
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
