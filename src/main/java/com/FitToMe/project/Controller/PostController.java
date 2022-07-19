package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.DTO.PostDTO;
import com.FitToMe.project.Request.PostModifyRequest;
import com.FitToMe.project.Request.PostRegisterRequest;
import com.FitToMe.project.Service.Post.PostDeleteService;
import com.FitToMe.project.Service.Post.PostModifyService;
import com.FitToMe.project.Service.Post.PostRegisterService;
import com.FitToMe.project.Service.Post.PostStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor    //check
public class PostController {

    private final PostRegisterService postRegisterService;
    private final PostModifyService postModifyService;
    private final PostDeleteService postDeleteService;
    private final PostStatusService postStatusService;

    // 전체 게시글 조회
    @GetMapping("/")
    public ApiResult<List<PostDTO>> readAllPosts() {
        return ApiResult.SUCCESS(postStatusService.findAll());
    }

    // 특정 게시글 조회
    @GetMapping("/{post_id}")
    public ApiResult<PostDTO> readPostOnlyOne(@PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postStatusService.findOne(postId));
    }

    // 게시글 등록
    @PostMapping("/")
    public ApiResult<PostDTO> createPost(@Valid @RequestBody PostRegisterRequest postRegisterRequest) {
        return ApiResult.SUCCESS(postRegisterService.createPost(postRegisterRequest));
    }

    // 게시글 수정
    @PutMapping("/{post_id}")
    public ApiResult<PostDTO> updatePost(@PathVariable(name = "post_id") Long postId, @RequestBody PostModifyRequest postModifyRequest) {
        return ApiResult.SUCCESS(postModifyService.updatePost(postId, postModifyRequest));
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public ApiResult<Boolean> deletePost(@PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postDeleteService.deletePost(postId));
    }
}
