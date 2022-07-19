package com.FitToMe.project.Entity.Post;

import com.FitToMe.project.Entity.User.User;
import com.FitToMe.project.Request.PostRegisterRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "post")
@ToString
@Getter
@Setter
@Builder            // 순서와 상관없이 만들뿐만 아니라 입력하지 않은 값은 자동으로 NULL을 넣어 생성할 수 있게 함
@AllArgsConstructor // 생성된 모든 멤버변수를 인자로 받는 생성자 민듦
@NoArgsConstructor  // 객체 생성 시 초기 인자 없이 객체 생성 가능
public class PostEntity extends TimeEntity {

    @Id // PK field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙: 자동 생성 전략 명시
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)   // FK field
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;
    private String content;
    private String imageURL;

    public PostEntity(PostRegisterRequest postRegisterRequest) {
        this.title = postRegisterRequest.getTitle();
        this.content = postRegisterRequest.getContent();
        this.imageURL = postRegisterRequest.getImageURL();
    }
}
