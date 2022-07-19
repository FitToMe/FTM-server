package com.FitToMe.project.Service.Post;

import com.FitToMe.project.Entity.Post;
import com.FitToMe.project.Repository.PostRepository;
import com.FitToMe.project.Request.PostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostModifyService {

    private final PostRepository postRepository;

    public com.FitToMe.project.DTO.PostDTO updatePost(Long postId, PostModifyRequest postModifyRequest) throws IllegalArgumentException {    //예외처리 check
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        if (postModifyRequest.getTitle() != null) {
            post.setTitle(postModifyRequest.getTitle());
        }
        if (postModifyRequest.getContent() != null) {
            post.setContent(postModifyRequest.getContent());
        }
        if (postModifyRequest.getImageURL() != null) {
            post.setImageURL(postModifyRequest.getImageURL());
        }

        return new com.FitToMe.project.DTO.PostDTO(post); // Dirty Checking 때문에 repository.save() 호출하지 않아도 변경사항이 DB에 반영된다.
    }

}