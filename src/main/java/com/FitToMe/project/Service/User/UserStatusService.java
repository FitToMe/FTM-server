package com.FitToMe.project.Service.User;

import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Entity.User;
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
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다"));

        return new UserDTO(user);
    }
}
