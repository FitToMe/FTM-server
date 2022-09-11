package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Entity.Comment.CommunityComment;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Exception.CustomError;
import com.FitToMe.project.Exception.CustomException;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Request.Comment.CommunityCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommunityCommentRegisterService {

    private final CommunityCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository postRepository;

    @Transactional
    public CommunityCommentDTO addComment(CommunityCommentRequest commentRequest, User user, Long postId) {

        if (user == null) {
            throw new CustomException(CustomError.NEED_LOGIN);
        }

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_EXIST));

        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomError.POST_NOT_EXIST));

        CommunityComment comment = commentRepository.save(CommunityComment.createComment(commentRequest, user, post));
        return new CommunityCommentDTO(comment);
    }
}
