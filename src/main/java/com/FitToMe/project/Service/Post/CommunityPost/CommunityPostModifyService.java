package com.FitToMe.project.Service.Post.CommunityPost;

import com.FitToMe.project.DTO.Post.CommunityPostDTO;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import com.FitToMe.project.Request.Post.CommunityPostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostModifyService {

    private final CommunityPostRepository communityPostRepository;

    public CommunityPostDTO updatePost(User user, Long postId, CommunityPostModifyRequest postModifyRequest) {
        CommunityPost post = communityPostRepository.findById(postId)
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

        return new CommunityPostDTO(post);
    }

}