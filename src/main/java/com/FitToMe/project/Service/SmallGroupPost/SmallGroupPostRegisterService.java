package com.FitToMe.project.Service.SmallGroupPost;

import com.FitToMe.project.DTO.SmallGroupPostDTO;
import com.FitToMe.project.Entity.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.SmallGroupPostRepository;
import com.FitToMe.project.Request.SmallGroupPostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmallGroupPostRegisterService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public SmallGroupPostDTO createPost(User user, SmallGroupPostRegisterRequest postRegisterRequest) {
        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        return new SmallGroupPostDTO(smallGroupPostRepository.save(new SmallGroupPost(user, postRegisterRequest)));
    }
}
