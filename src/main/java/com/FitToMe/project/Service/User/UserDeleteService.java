package com.FitToMe.project.Service.User;

import com.FitToMe.project.Entity.User;
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
            throw new RuntimeException("존재하지 않는 회원입니다");
        }

        userRepository.delete(user);
        return true;
    }
}
