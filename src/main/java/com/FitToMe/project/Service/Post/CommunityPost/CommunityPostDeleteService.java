package com.FitToMe.project.Service.Post.CommunityPost;

import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityPostDeleteService {

    private final CommunityPostRepository communityPostRepository;

    public boolean deletePost(User user, Long postId) {
        CommunityPost post = communityPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        if (!post.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_DELETE_POST);
        }

        communityPostRepository.deleteById(postId);
        return true;
    }
}
