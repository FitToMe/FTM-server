package com.FitToMe.project.Service.Comment.SmallGroupComment;

import com.FitToMe.project.DTO.Comment.SmallGroupCommentDTO;
import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Comment.SmallGroupCommentRepository;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.Comment.SmallGroupCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SmallGroupCommentRegisterService {

    private final SmallGroupCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SmallGroupPostRepository postRepository;

    @Transactional
    public SmallGroupCommentDTO addComment(SmallGroupCommentRequest commentRequest, User user, Long postId) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_EXIST));

        SmallGroupPost post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        SmallGroupComment comment = commentRepository.save(SmallGroupComment.createComment(commentRequest, user, post));
        return new SmallGroupCommentDTO(comment);
    }
}
