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
public class ClassCommentModifyService {
    private final ClassCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ClassPostRepository postRepository;

    @Transactional
    public ClassCommentDTO updateComment(User user, Long postId, Long commentId, ClassCommentRequest commentRequest) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        ClassPost post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        ClassComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CustomError.COMMENT_NOT_EXIST));

        if (!comment.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_MODIFY_COMMENT);
        }

        return new ClassCommentDTO(comment);
    }
}
