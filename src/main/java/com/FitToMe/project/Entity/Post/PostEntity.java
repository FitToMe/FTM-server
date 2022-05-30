package com.FitToMe.project.Entity.Post;

import com.FitToMe.project.Entity.User.User;
import com.FitToMe.project.Request.PostRequest;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "post")
@ToString
@Getter
@Builder            // 순서와 상관없이 만들뿐만 아니라 입력하지 않은 값은 자동으로 NULL을 넣어 생성할 수 있게 함
@AllArgsConstructor // 생성된 모든 멤버변수를 인자로 받는 생성자 민듦
@NoArgsConstructor  // 객체 생성 시 초기 인자 없이 객체 생성 가능
public class PostEntity extends TimeEntity {

    @Id // PK field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙: 자동 생성 전략 명시
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)   // FK field
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;
    private String content;
    private String imageURL;

    public PostEntity(PostRequest postRequest) {

        // Q. User 객체에 대한 정보는 안 넣어줘도 되나?
        this(postRequest.getTitle(), postRequest.getContent(), postRequest.getImageURL());
    }

    // Q. User 객체에 대한 정보는 안 넣어줘도 되나?
    public PostEntity(String title, String content, String imageURL){
        this.title=title;
        this.content=content;
        this.imageURL=imageURL;
    }
}
