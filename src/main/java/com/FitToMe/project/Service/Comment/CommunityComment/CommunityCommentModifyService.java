package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Entity.Comment.CommunityComment;
import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.*;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
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
            throw new RuntimeException("로그인이 필요합니다");
        }

        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        CommunityComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다"));

        if (!comment.getUser().equals(user)) {
            throw new SecurityException("해당 댓글을 수정할 수 있는 권한이 없습니다");
        }

        return new CommunityCommentDTO(comment);
    }
}
