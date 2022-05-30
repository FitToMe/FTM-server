package com.FitToMe.project.Service.Post;

import com.FitToMe.project.Entity.Post.PostEntity;
import com.FitToMe.project.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)     //조회만 하는 경우 사용하는 속성
@Service
@RequiredArgsConstructor
public class PostStatusService {
    
    private final PostRepository postRepository;

    public PostEntity findPostById(Long postId) throws NullPointerException{
        Optional<PostEntity> post = postRepository.findById(postId);
        if(post.isPresent()){
            return post.get();
        }else{
            throw new NullPointerException("해당 postId 게시판이 존재하지 않습니다");
        }
    }

//    public PostEntity findPostByTitle(String postTitle) throws NullPointerException{
//        Optional<PostEntity> post = PostRepository.findByTitle(postTitle);  //여기랑 repository 이상함
//        if(post.isPresent()){
//            return post.get();
//        }else{
//            throw new NullPointerException("해당 postTitle 게시판이 존재하지 않습니다");
//        }
//    }

    public List<PostEntity> findPosts() throws NullPointerException{  
        List<PostEntity> posts = postRepository.findAll();
        if(posts!=null){
            return posts;
        }else{
            // 실제 값이 아닌 null(아무것도 아닌 값)을 가지고 있는 객체 / 변수 등을 호출할 경우를 예외처리함
            throw new NullPointerException("현재 존재하는 게시판이 없습니다");
        }
    }
}
