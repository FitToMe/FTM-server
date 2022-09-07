package com.FitToMe.project.Controller.Post;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.Post.SmallGroupPostDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Post.SmallGroupPostModifyRequest;
import com.FitToMe.project.Request.Post.SmallGroupPostRegisterRequest;
import com.FitToMe.project.Service.Post.SmallGroupPost.SmallGroupPostDeleteService;
import com.FitToMe.project.Service.Post.SmallGroupPost.SmallGroupPostModifyService;
import com.FitToMe.project.Service.Post.SmallGroupPost.SmallGroupPostRegisterService;
import com.FitToMe.project.Service.Post.SmallGroupPost.SmallGroupPostStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "small group post", description = "소모임 게시글 API")
@RestController
@RequestMapping("/small-group-post")
@RequiredArgsConstructor    //check
public class SmallGroupPostController {

    private final SmallGroupPostRegisterService postRegisterService;
    private final SmallGroupPostModifyService postModifyService;
    private final SmallGroupPostDeleteService postDeleteService;
    private final SmallGroupPostStatusService postStatusService;

    @Operation(summary = "전체 게시글 조회")
    @GetMapping("/")
    public ApiResult<List<SmallGroupPostDTO>> readAllPosts() {
        return ApiResult.SUCCESS(postStatusService.findAll());
    }

    @Operation(summary = "특정 게시글 조회")
    @GetMapping("/{post_id}")
    public ApiResult<SmallGroupPostDTO> readPostOnlyOne(@PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postStatusService.findOne(postId));
    }

    @Operation(summary = "새로운 게시글 등록")
    @PostMapping("/")
    public ApiResult<SmallGroupPostDTO> createPost(@AuthUser User user, @Valid @RequestBody SmallGroupPostRegisterRequest postRegisterRequest) {
        return ApiResult.SUCCESS(postRegisterService.createPost(user, postRegisterRequest));
    }

    @Operation(summary = "특정 게시글 수정")
    @PutMapping("/{post_id}")
    public ApiResult<SmallGroupPostDTO> updatePost(@AuthUser User user, @PathVariable(name = "post_id") Long postId, @RequestBody SmallGroupPostModifyRequest postModifyRequest) {
        return ApiResult.SUCCESS(postModifyService.updatePost(user, postId, postModifyRequest));
    }

    @Operation(summary = "특정 게시글 삭제")
    @DeleteMapping("/{post_id}")
    public ApiResult<Boolean> deletePost(@AuthUser User user, @PathVariable("post_id") Long postId) {
        return ApiResult.SUCCESS(postDeleteService.deletePost(user, postId));
    }
}
