package com.FitToMe.project.Service.Post.SmallGroupPost;

import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmallGroupPostDeleteService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public boolean deletePost(User user, Long postId) {
        SmallGroupPost post = smallGroupPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        if (!post.getUser().equals(user)) {
            throw new SecurityException("해당 게시글을 삭제할 수 있는 권한이 없습니다");
        }

        smallGroupPostRepository.deleteById(postId);
        return true;
    }
}
