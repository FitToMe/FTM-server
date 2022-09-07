package com.FitToMe.project.Controller.Comment;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.Comment.SmallGroupCommentDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.SmallGroupCommentRequest;
import com.FitToMe.project.Service.Comment.SmallGroupComment.SmallGroupCommentDeleteService;
import com.FitToMe.project.Service.Comment.SmallGroupComment.SmallGroupCommentModifyService;
import com.FitToMe.project.Service.Comment.SmallGroupComment.SmallGroupCommentRegisterService;
import com.FitToMe.project.Service.Comment.SmallGroupComment.SmallGroupCommentSearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SmallGroupCommentController {
    private final SmallGroupCommentRegisterService commentRegisterService;
    private final SmallGroupCommentModifyService commentModifyService;
    private final SmallGroupCommentSearchService commentSearchService;
    private final SmallGroupCommentDeleteService commentDeleteService;


    @Operation(summary = "커뮤니티 게시글에 댓글 작성")
    @PostMapping("/smallGroupPost/{smallGroupPostId}/comments")
    public ApiResult<SmallGroupCommentDTO> addComment(@AuthUser User user, @PathVariable Long smallGroupPostId, @Valid @RequestBody SmallGroupCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentRegisterService.addComment(commentRequest, user, smallGroupPostId));
    }

    @Operation(summary = "커뮤니티 게시글의 전체 댓글 조회")
    @GetMapping("/smallGroupPost/{smallGroupPostId}/comments")
    public ApiResult<List<SmallGroupCommentDTO>> readAllComments(@PathVariable Long smallGroupPostId) {
        return ApiResult.SUCCESS(commentSearchService.findBySmallGroupId(smallGroupPostId));
    }

    @Operation(summary = "특정 유저의 커뮤니티 게시글의 댓글 목록 조회")
    @GetMapping("/comments/users/{userId}")
    public ApiResult<List<SmallGroupCommentDTO>> readOnlyUserComment(@PathVariable Long userId) {
        return ApiResult.SUCCESS(commentSearchService.findByUserId(userId));
    }

    @Operation(summary = "커뮤니티 게시글의 특정 댓글 수정")
    @PutMapping("/smallGroupPost/{smallGroupPostId}/comments/{commentId}")
    public ApiResult<SmallGroupCommentDTO> updateComment(@AuthUser User user, @PathVariable Long smallGroupPostId, @PathVariable Long commentId, @Valid @RequestBody SmallGroupCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentModifyService.updateComment(user, smallGroupPostId, commentId, commentRequest));
    }

    @Operation(summary = "커뮤니티 게시글의 특정 댓글 삭제")
    @DeleteMapping("/smallGroupPost/{smallGroupPostId}/comments/{commentId}")
    public ApiResult<Boolean> deleteComment(@AuthUser User user, @PathVariable Long smallGroupPostId, @PathVariable Long commentId) {
        return ApiResult.SUCCESS(commentDeleteService.deleteComment(commentId, user));
    }
}
