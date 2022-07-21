package com.FitToMe.project.DTO;

import com.FitToMe.project.Entity.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private final Long id;
    private final String email;
    private final String nickname;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
