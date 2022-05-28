package com.FitToMe.project.Controller;

import com.FitToMe.project.Request.PostRequest;
import com.FitToMe.project.Service.Post.PostDeleteService;
import com.FitToMe.project.Service.Post.PostModifyService;
import com.FitToMe.project.Service.Post.PostRegisterService;
import com.FitToMe.project.Service.Post.PostStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor    //check
public class PostController {

    PostRegisterService postRegisterService;
    PostModifyService postModifyService;
    PostDeleteService postDeleteService;
    PostStatusService postStatusService;

    // 전체 게시글 조회
    @GetMapping("/")
    public void readList(){
        postStatusService.findPosts();
    }

    // 특정 게시글 조회
    @GetMapping("/{post_id}")
    public void readOne(@PathVariable("post_id") Long postId){
        postStatusService.findPostById(postId);
    }

    // 게시글 등록
    @PostMapping("/")
    public void create(PostRequest postRequest){
        postRegisterService.createPost(postRequest);
    }

    // 게시글 수정
    @PutMapping("/{post_id}")
    public void update(@PathVariable("post_id") Long postId, PostRequest postRequest){
        postModifyService.updatePost(postRequest);
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public void delete(@PathVariable("post_id") Long postId){
        postDeleteService.deletePost(postId);
    }

}
