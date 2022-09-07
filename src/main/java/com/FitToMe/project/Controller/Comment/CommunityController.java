package com.FitToMe.project.Controller.Comment;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.CommunityCommentRequest;
import com.FitToMe.project.Service.Comment.CommunityComment.CommunityCommentDeleteService;
import com.FitToMe.project.Service.Comment.CommunityComment.CommunityCommentModifyService;
import com.FitToMe.project.Service.Comment.CommunityComment.CommunityCommentRegisterService;
import com.FitToMe.project.Service.Comment.CommunityComment.CommunityCommentSearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityCommentRegisterService commentRegisterService;
    private final CommunityCommentModifyService commentModifyService;
    private final CommunityCommentSearchService commentSearchService;
    private final CommunityCommentDeleteService commentDeleteService;


    @Operation(summary = "커뮤니티 게스글에 댓글 작성")
    @PostMapping("/communityPost/{communityPostId}/comments")
    public ApiResult<CommunityCommentDTO> addComment(@AuthUser User user, @PathVariable Long communityPostId, @Valid @RequestBody CommunityCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentRegisterService.addComment(commentRequest, user, communityPostId));
    }

    @Operation(summary = "커뮤니티 게시글의 전체 댓글 조회")
    @GetMapping("/communityPost/{communityPostId}/comments")
    public ApiResult<List<CommunityCommentDTO>> readAllComments(@PathVariable Long communityPostId) {
        return ApiResult.SUCCESS(commentSearchService.findByCommunityPostId(communityPostId));
    }

    @Operation(summary = "특정 유저의 커뮤니티 게시글의 댓글 목록 조회")
    @GetMapping("/comments/users/{userId}")
    public ApiResult<List<CommunityCommentDTO>> readOnlyUserComment(@PathVariable Long userId) {
        return ApiResult.SUCCESS(commentSearchService.findByUserId(userId));
    }

    @Operation(summary = "커뮤니티 게시글의 특정 댓글 수정")
    @PutMapping("/communityPost/{communityPostId}/comments/{commentId}")
    public ApiResult<CommunityCommentDTO> updateComment(@AuthUser User user, @PathVariable Long communityPostId, @PathVariable Long commentId, @Valid @RequestBody CommunityCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentModifyService.updateComment(user, communityPostId, commentId, commentRequest));
    }

    @Operation(summary = "커뮤니티 게시글의 특정 댓글 삭제")
    @DeleteMapping("/communityPost/{communityPostId}/comments/{commentId}")
    public ApiResult<Boolean> deleteComment(@AuthUser User user, @PathVariable Long communityPostId, @PathVariable Long commentId) {
        return ApiResult.SUCCESS(commentDeleteService.deleteComment(commentId, user));
    }
}
