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
public class PostModifyService {

    private final PostRepository postRepository;

    public PostDTO updatePost(PostRequest postRequest) throws IllegalArgumentException{    //예외처리 check
        String postTitle = postRequest.getTitle();
        Optional<PostEntity> postEntity = postRepository.findAllTitle(postTitle);

        if(postEntity.isPresent()){
            return new PostDTO(postRepository.save(PostEntity.builder()
                            .title(postRequest.getTitle())
                            .content(postRequest.getContent())
                            .imageURL(postRequest.getImageURL())
                            .build()));
        }else{
            throw new IllegalArgumentException("해당 게사판은 존재하지 않습니다");
        }
    }
}