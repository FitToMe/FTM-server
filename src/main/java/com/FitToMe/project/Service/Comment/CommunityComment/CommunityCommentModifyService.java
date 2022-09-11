package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Entity.Comment.CommunityComment;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.*;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import com.FitToMe.project.Request.Comment.CommunityCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommunityCommentModifyService {
    private final CommunityCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository postRepository;

    @Transactional
    public CommunityCommentDTO updateComment(User user, Long postId, Long commentId, CommunityCommentRequest commentRequest) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        CommunityComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CustomError.COMMENT_NOT_EXIST));

        if (!comment.getUser().equals(user)) {
            throw new CustomException(CustomError.NO_AUTHORITY_TO_MODIFY_COMMENT);
        }

        return new CommunityCommentDTO(comment);
    }
}
