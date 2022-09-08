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
public class ClassCommentRegisterService {

    private final ClassCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ClassPostRepository postRepository;

    @Transactional
    public ClassCommentDTO addComment(ClassCommentRequest commentRequest, User user, Long postId) {

        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        user = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));

        ClassPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        ClassComment comment = commentRepository.save(ClassComment.createComment(commentRequest, user, post));
        return new ClassCommentDTO(comment);
    }
}
