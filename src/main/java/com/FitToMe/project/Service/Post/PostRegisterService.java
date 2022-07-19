package com.FitToMe.project.Service.Post;

import com.FitToMe.project.DTO.PostDTO;
import com.FitToMe.project.Entity.Post.PostEntity;
import com.FitToMe.project.Repository.PostRepository;
import com.FitToMe.project.Request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostRegisterService {

    private final PostRepository postRepository;

    public PostDTO createPost(PostRequest postRequest) throws IllegalArgumentException{

        String postTitle = postRequest.getTitle();
        Optional<PostEntity> postEntity = postRepository.findByTitle(postTitle);

        if(!postEntity.isPresent()){    // 존재하지 않은 경우 entity 생성
            return new PostDTO(postRepository.save(PostEntity.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .imageURL(postRequest.getImageURL())
                    .build()));
        }else{
            // 적합하지 않거나(illgal) 적절하지 못한(inappropriate)인자를 메소드에 넘겨주었을 때 발생하는 예외를 처리함
            throw new IllegalArgumentException("해당 게사판 이름은 이미 존재합니다!");
        }
    }
}
