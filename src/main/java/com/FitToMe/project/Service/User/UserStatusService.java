package com.FitToMe.project.Service.User;

import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserStatusService {

    private final UserRepository userRepository;

    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_EXIST));

        return new UserDTO(user);
    }
}
