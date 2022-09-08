package com.FitToMe.project.Service.Comment.SmallGroupComment;

import com.FitToMe.project.DTO.Comment.SmallGroupCommentDTO;
import com.FitToMe.project.Entity.Comment.SmallGroupComment;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.User;
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
public class SmallGroupCommentModifyService {
    private final SmallGroupCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SmallGroupPostRepository postRepository;


    @Transactional
    public SmallGroupCommentDTO updateComment(User user, Long postId, Long commentId, SmallGroupCommentRequest commentRequest) {

        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        SmallGroupPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        SmallGroupComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다"));

        if (!comment.getUser().equals(user)) {
            throw new SecurityException("해당 댓글을 수정할 수 있는 권한이 없습니다");
        }

        return new SmallGroupCommentDTO(comment);
    }
}
