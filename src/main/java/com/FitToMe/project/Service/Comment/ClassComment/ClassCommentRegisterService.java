package com.FitToMe.project.Service.Comment.ClassComment;

import com.FitToMe.project.DTO.Comment.ClassCommentDTO;
import com.FitToMe.project.Entity.Comment.ClassComment;
import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Comment.ClassCommentRepository;
import com.FitToMe.project.Repository.Post.ClassPostRepository;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.Comment.ClassCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ClassCommentRegisterService {

    private final ClassCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ClassPostRepository postRepository;

    @Transactional
    public ClassCommentDTO addComment(ClassCommentRequest commentRequest, User user, Long postId) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_EXIST));

        ClassPost post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        ClassComment comment = commentRepository.save(ClassComment.createComment(commentRequest, user, post));
        return new ClassCommentDTO(comment);
    }
}
