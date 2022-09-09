package com.FitToMe.project.Service.Comment.ClassComment;

import com.FitToMe.project.Entity.Comment.ClassComment;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Comment.ClassCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ClassCommentDeleteService {
    private final ClassCommentRepository commentRepository;

    @Transactional
    public Boolean deleteComment(Long commentId, User user) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        ClassComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CustomError.COMMENT_NOT_EXIST));

        if (!comment.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_DELETE_COMMENT);
        }

        commentRepository.deleteById(commentId);
        return true;
    }
}
