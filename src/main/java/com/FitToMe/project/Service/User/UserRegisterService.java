package com.FitToMe.project.Service.User;

import com.FitToMe.project.DTO.UserDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signup(UserSignupRequest userSignupRequest) {
        if (userRepository.existsByEmail(userSignupRequest.getEmail())) {
            throw new RuntimeException("이미 사용중인 email 주소입니다");
        }

        User newUser = new User(userSignupRequest, passwordEncoder);
        return new UserDTO(userRepository.save(newUser));
    }
}
