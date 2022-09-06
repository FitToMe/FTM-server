package com.FitToMe.project.Service.SmallGroupPost;

import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.DTO.SmallGroupPostDTO;
import com.FitToMe.project.Entity.ClassPost;
import com.FitToMe.project.Entity.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Repository.SmallGroupPostRepository;
import com.FitToMe.project.Request.ClassPostModifyRequest;
import com.FitToMe.project.Request.SmallGroupPostModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmallGroupPostModifyService {

    private final SmallGroupPostRepository smallGroupPostRepository;

    public SmallGroupPostDTO updatePost(User user, Long postId, SmallGroupPostModifyRequest postModifyRequest) {
        SmallGroupPost post = smallGroupPostRepository.findById(postId)
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

        return new SmallGroupPostDTO(post);
    }

}