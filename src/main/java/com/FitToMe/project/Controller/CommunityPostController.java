package com.FitToMe.project.Controller;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.CommunityPostDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.CommunityPostModifyRequest;
import com.FitToMe.project.Request.CommunityPostRegisterRequest;
import com.FitToMe.project.Service.CommunityPost.CommunityPostDeleteService;
import com.FitToMe.project.Service.CommunityPost.CommunityPostModifyService;
import com.FitToMe.project.Service.CommunityPost.CommunityPostRegisterService;
import com.FitToMe.project.Service.CommunityPost.CommunityPostStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "community post", description = "커뮤니티 게시글 API")
@RestController
@RequestMapping("/community-post")
@RequiredArgsConstructor    //check
public class CommunityPostController {

    private final CommunityPostRegisterService postRegisterService;
    private final CommunityPostModifyService postModifyService;
    private final CommunityPostDeleteService postDeleteService;
    private final CommunityPostStatusService postStatusService;

    @Operation(summary = "전체 게시글 조회")
    @GetMapping("/")
    public ApiResult<List<CommunityPostDTO>> readAllPosts() {
        return ApiResult.SUCCESS(postStatusService.findAll());
    }

    @Operation(summary = "특정 게시글 조회")
    @GetMapping("/{post_id}")
    public ApiResult<CommunityPostDTO> readPostOnlyOne(@PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postStatusService.findOne(postId));
    }

    @Operation(summary = "새로운 게시글 등록")
    @PostMapping("/")
    public ApiResult<CommunityPostDTO> createPost(@AuthUser User user, @Valid @RequestBody CommunityPostRegisterRequest postRegisterRequest) {
        return ApiResult.SUCCESS(postRegisterService.createPost(user, postRegisterRequest));
    }

    @Operation(summary = "특정 게시글 수정")
    @PutMapping("/{post_id}")
    public ApiResult<CommunityPostDTO> updatePost(@AuthUser User user, @PathVariable(name = "post_id") Long postId, @RequestBody CommunityPostModifyRequest postModifyRequest) {
        return ApiResult.SUCCESS(postModifyService.updatePost(user, postId, postModifyRequest));
    }

    @Operation(summary = "특정 게시글 삭제")
    @DeleteMapping("/{post_id}")
    public ApiResult<Boolean> deletePost(@AuthUser User user, @PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postDeleteService.deletePost(user, postId));
    }
}
