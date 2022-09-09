package com.FitToMe.project.Service.User;

import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    public boolean delete(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new CustomException(CustomError.USER_NOT_EXIST);
        }

        userRepository.delete(user);
        return true;
    }
}
