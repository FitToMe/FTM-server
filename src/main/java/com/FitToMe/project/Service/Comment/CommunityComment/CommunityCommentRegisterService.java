package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Entity.Comment.CommunityComment;
import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
import com.FitToMe.project.Repository.CommunityPostRepository;
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
            throw new RuntimeException("로그인이 필요합니다");
        }

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));

        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        CommunityComment comment = commentRepository.save(CommunityComment.createComment(commentRequest, user, post));
        return new CommunityCommentDTO(comment);
    }
}
