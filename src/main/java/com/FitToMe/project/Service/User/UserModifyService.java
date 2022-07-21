package com.FitToMe.project.Service.User;

import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.UserModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserModifyService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO modifyUser(User user, UserModifyRequest userModifyRequest) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("존재하지 않는 회원입니다");
        }

        if (userModifyRequest.getNickname() != null) {
            user.setNickname(userModifyRequest.getNickname());
        }
        if (userModifyRequest.getEmail() != null) {
            if (userRepository.existsByEmail(userModifyRequest.getEmail())) {
                throw new RuntimeException("해당 email 계정은 이미 사용중입니다");
            }
            user.setEmail(userModifyRequest.getEmail());
        }
        if (userModifyRequest.getPassword() != null) {
            user.setPassword(userModifyRequest.getPassword(), passwordEncoder);
        }

        return new UserDTO(userRepository.save(user));
    }
}
