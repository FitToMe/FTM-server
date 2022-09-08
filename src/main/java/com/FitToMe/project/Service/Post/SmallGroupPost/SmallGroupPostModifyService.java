package com.FitToMe.project.Service.Post.SmallGroupPost;

import com.FitToMe.project.DTO.Post.SmallGroupPostDTO;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import com.FitToMe.project.Request.Post.SmallGroupPostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmallGroupPostModifyService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public SmallGroupPostDTO updatePost(User user, Long postId, SmallGroupPostModifyRequest postModifyRequest) {
        SmallGroupPost post = smallGroupPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        if (!post.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_MODIFY_POST);
        }
        if (postModifyRequest.getTitle() != null) {
            post.setTitle(postModifyRequest.getTitle());
        }
        if (postModifyRequest.getContent() != null) {
            post.setContent(postModifyRequest.getContent());
        }
        if (postModifyRequest.getImageURL() != null) {
            post.setImageURL(postModifyRequest.getImageURL());
        }

        return new SmallGroupPostDTO(post);
    }

}