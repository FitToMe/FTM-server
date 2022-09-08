package com.FitToMe.project.Service.SmallGroupPost;

import com.FitToMe.project.Entity.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.SmallGroupPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmallGroupPostDeleteService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public boolean deletePost(User user, Long postId) {
        SmallGroupPost post = smallGroupPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        if (!post.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_DELETE_POST);
        }

        smallGroupPostRepository.deleteById(postId);
        return true;
    }
}
