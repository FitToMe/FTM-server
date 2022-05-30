package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.DTO.PostDTO;
import com.FitToMe.project.Request.PostRequest;
import com.FitToMe.project.Service.Post.PostDeleteService;
import com.FitToMe.project.Service.Post.PostModifyService;
import com.FitToMe.project.Service.Post.PostRegisterService;
import com.FitToMe.project.Service.Post.PostStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ApiResult<List<PostDTO>> readPostList(){

        // check
        return ApiResult.SUCCESS(postStatusService.findPosts().stream().map(PostDTO::new).collect(Collectors.toList()));
    }

    // 특정 게시글 조회
//    @GetMapping("/{post_id}")
//    public ApiResult<PostDTO> readPostOnlyOne(@PathVariable("post_id") Long postId){
//        return ApiResult.SUCCESS(postStatusService.findPostById(postId));
//    }

    // 게시글 등록
    @PostMapping("/")
    public ApiResult<PostDTO> createPost(@Valid @RequestBody PostRequest postRequest){
        return ApiResult.SUCCESS(postRegisterService.createPost(postRequest));
    }

    // 게시글 수정
    @PutMapping("/{post_id}")
    public ApiResult<PostDTO> updatePost(@Valid @RequestBody PostRequest postRequest){
        return ApiResult.SUCCESS(postModifyService.updatePost(postRequest));
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public ApiResult<Boolean> deletePost(@PathVariable("post_id") Long postId){
        return ApiResult.SUCCESS(postDeleteService.deletePost(postId));
    }
}
