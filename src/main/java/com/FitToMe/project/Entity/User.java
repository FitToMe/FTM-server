package com.FitToMe.project.Entity;

import com.FitToMe.project.Request.UserSignupRequest;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthority role;

    public User(UserSignupRequest userSignupRequest, PasswordEncoder passwordEncoder) {
        this.email = userSignupRequest.getEmail();
        this.password = passwordEncoder.encode(userSignupRequest.getPassword());
        this.nickname = userSignupRequest.getNickname();
        this.role = UserAuthority.NORMAL;
    }

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
