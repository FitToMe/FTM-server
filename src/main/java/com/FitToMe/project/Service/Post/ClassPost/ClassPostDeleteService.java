package com.FitToMe.project.Service.Post.ClassPost;

import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Post.ClassPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassPostDeleteService {

    private final ClassPostRepository classPostRepository;

    public boolean deletePost(User user, Long postId) {
        ClassPost post = classPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        if (!post.getUser().equals(user)){
            throw new CustomException(CustomError.NO_AUTHORITY_TO_DELETE_POST);
        }

        classPostRepository.deleteById(postId);
        return true;
    }
}
