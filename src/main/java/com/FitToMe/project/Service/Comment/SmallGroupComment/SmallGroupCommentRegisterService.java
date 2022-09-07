package com.FitToMe.project.Service.Comment.SmallGroupComment;

import com.FitToMe.project.DTO.Comment.SmallGroupCommentDTO;
import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import com.FitToMe.project.Entity.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.Comment.SmallGroupCommentRepository;
import com.FitToMe.project.Repository.SmallGroupPostRepository;
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
            throw new RuntimeException("로그인이 필요합니다");
        }

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));

        SmallGroupPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        SmallGroupComment comment = commentRepository.save(SmallGroupComment.createComment(commentRequest, user, post));
        return new SmallGroupCommentDTO(comment);
    }
}
