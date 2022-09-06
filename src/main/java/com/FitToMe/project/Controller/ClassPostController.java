package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.ClassPostDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.ClassPostModifyRequest;
import com.FitToMe.project.Request.ClassPostRegisterRequest;
import com.FitToMe.project.Service.ClassPost.ClassPostDeleteService;
import com.FitToMe.project.Service.ClassPost.ClassPostModifyService;
import com.FitToMe.project.Service.ClassPost.ClassPostRegisterService;
import com.FitToMe.project.Service.ClassPost.ClassPostStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "class post", description = "클래스 게시글 API")
@RestController
@RequestMapping("/class-post")
@RequiredArgsConstructor    //check
public class ClassPostController {

    private final ClassPostRegisterService postRegisterService;
    private final ClassPostModifyService postModifyService;
    private final ClassPostDeleteService postDeleteService;
    private final ClassPostStatusService postStatusService;

    @Operation(summary = "전체 게시글 조회")
    @GetMapping("/")
    public ApiResult<List<ClassPostDTO>> readAllPosts() {
        return ApiResult.SUCCESS(postStatusService.findAll());
    }

    @Operation(summary = "특정 게시글 조회")
    @GetMapping("/{post_id}")
    public ApiResult<ClassPostDTO> readPostOnlyOne(@PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postStatusService.findOne(postId));
    }

    @Operation(summary = "새로운 게시글 등록")
    @PostMapping("/")
    public ApiResult<ClassPostDTO> createPost(@AuthUser User user, @Valid @RequestBody ClassPostRegisterRequest postRegisterRequest) {
        return ApiResult.SUCCESS(postRegisterService.createPost(user, postRegisterRequest));
    }

    @Operation(summary = "특정 게시글 수정")
    @PutMapping("/{post_id}")
    public ApiResult<ClassPostDTO> updatePost(@AuthUser User user, @PathVariable(name = "post_id") Long postId, @RequestBody ClassPostModifyRequest postModifyRequest) {
        return ApiResult.SUCCESS(postModifyService.updatePost(user, postId, postModifyRequest));
    }

    @Operation(summary = "특정 게시글 삭제")
    @DeleteMapping("/{post_id}")
    public ApiResult<Boolean> deletePost(@AuthUser User user, @PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postDeleteService.deletePost(user, postId));
    }
}
