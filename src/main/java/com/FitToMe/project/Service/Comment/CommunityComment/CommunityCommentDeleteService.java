package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.Entity.Comment.CommunityComment;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommunityCommentDeleteService {
    private final CommunityCommentRepository commentRepository;

    @Transactional
    public Boolean deleteComment(Long commentId, User user) {

        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        CommunityComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().equals(user)) {
            throw new SecurityException("해당 댓글을 삭제할 수 있는 권한이 없습니다");
        }

        commentRepository.deleteById(commentId);
        return true;
    }
}
