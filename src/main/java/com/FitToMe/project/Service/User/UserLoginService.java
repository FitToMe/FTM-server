package com.FitToMe.project.Service.User;

import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public boolean login(LoginRequest loginRequest) {
        if (!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new CustomException(CustomError.NOT_FOUND_EMAIL);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}
