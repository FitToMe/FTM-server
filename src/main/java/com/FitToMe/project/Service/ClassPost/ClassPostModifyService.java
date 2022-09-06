package com.FitToMe.project.Service.ClassPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.DTO.CommunityPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.ClassPostRepository;
import com.FitToMe.project.Request.ClassPostModifyRequest;
import com.FitToMe.project.Request.CommunityPostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassPostModifyService {

    private final ClassPostRepository classPostRepository;

    public ClassPostDTO updatePost(User user, Long postId, ClassPostModifyRequest postModifyRequest) {
        ClassPost post = classPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게사판은 존재하지 않습니다"));

        if (!post.getUser().equals(user)) {
            throw new SecurityException("해당 게시글을 수정할 수 있는 권한이 없습니다");
        }

        if (postModifyRequest.getTitle() != null) {
            post.setTitle(postModifyRequest.getTitle());
        }
        if (postModifyRequest.getContent() != null) {
            post.setContent(postModifyRequest.getContent());
        }
        if (postModifyRequest.getImageURL() != null) {
            post.setImageURL(postModifyRequest.getImageURL());
        }

        return new ClassPostDTO(post);
    }

}