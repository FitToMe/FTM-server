package com.FitToMe.project.Service.Comment.SmallGroupComment;

import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Comment.SmallGroupCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SmallGroupCommentDeleteService {
    private final SmallGroupCommentRepository commentRepository;

    @Transactional
    public Boolean deleteComment(Long commentId, User user) {

        SmallGroupComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CustomError.COMMENT_NOT_EXIST));

        if (!comment.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_DELETE_COMMENT);
        }

        commentRepository.deleteById(commentId);
        return true;
    }
}
