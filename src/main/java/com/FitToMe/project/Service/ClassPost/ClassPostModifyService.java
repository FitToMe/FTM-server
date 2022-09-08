package com.FitToMe.project.Service.ClassPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.ClassPostRepository;
import com.FitToMe.project.Request.ClassPostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassPostModifyService {

    private final ClassPostRepository classPostRepository;

    public ClassPostDTO updatePost(User user, Long postId, ClassPostModifyRequest postModifyRequest) {
        ClassPost post = classPostRepository.findById(postId)
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

        return new ClassPostDTO(post);
    }

}