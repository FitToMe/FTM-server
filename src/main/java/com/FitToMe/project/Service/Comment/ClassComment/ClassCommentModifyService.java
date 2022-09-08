package com.FitToMe.project.Service.Comment.ClassComment;

import com.FitToMe.project.DTO.Comment.ClassCommentDTO;
import com.FitToMe.project.Entity.Comment.ClassComment;
import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Entity.User;
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
            throw new RuntimeException("로그인이 필요합니다");
        }

        ClassPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        ClassComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다"));

        if (!comment.getUser().equals(user)) {
            throw new SecurityException("해당 댓글을 수정할 수 있는 권한이 없습니다");
        }

        return new ClassCommentDTO(comment);
    }
}
