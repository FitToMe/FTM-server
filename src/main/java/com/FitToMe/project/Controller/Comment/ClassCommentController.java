package com.FitToMe.project.Controller.Comment;

import com.FitToMe.project.ApiResult.ApiResult;
import com.FitToMe.project.Config.Security.AuthUser;
import com.FitToMe.project.DTO.Comment.ClassCommentDTO;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Request.Comment.ClassCommentRequest;
import com.FitToMe.project.Service.Comment.ClassComment.ClassCommentDeleteService;
import com.FitToMe.project.Service.Comment.ClassComment.ClassCommentModifyService;
import com.FitToMe.project.Service.Comment.ClassComment.ClassCommentRegisterService;
import com.FitToMe.project.Service.Comment.ClassComment.ClassCommentSearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classPost")
public class ClassCommentController {
    private final ClassCommentRegisterService commentRegisterService;
    private final ClassCommentModifyService commentModifyService;
    private final ClassCommentSearchService commentSearchService;
    private final ClassCommentDeleteService commentDeleteService;


    @Operation(summary = "클래스 게시글에 댓글 작성")
    @PostMapping("/{classPostId}/comments")
    public ApiResult<ClassCommentDTO> addComment(@AuthUser User user, @PathVariable Long classPostId, @Valid @RequestBody ClassCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentRegisterService.addComment(commentRequest, user, classPostId));
    }

    @Operation(summary = "클래스 게시글의 전체 댓글 조회")
    @GetMapping("/{classPostId}/comments")
    public ApiResult<List<ClassCommentDTO>> readAllComments(@PathVariable Long classPostId) {
        return ApiResult.SUCCESS(commentSearchService.findByClassPostId(classPostId));
    }

    @Operation(summary = "클래스 게시글의 특정 댓글 수정")
    @PutMapping("/{classPostId}/comments/{commentId}")
    public ApiResult<ClassCommentDTO> updateComment(@AuthUser User user, @PathVariable Long classPostId, @PathVariable Long commentId, @Valid @RequestBody ClassCommentRequest commentRequest) {
        return ApiResult.SUCCESS(commentModifyService.updateComment(user, classPostId, commentId, commentRequest));
    }

    @Operation(summary = "클래스 게시글의 특정 댓글 삭제")
    @DeleteMapping("/{classPostId}/comments/{commentId}")
    public ApiResult<Boolean> deleteComment(@AuthUser User user, @PathVariable Long classPostId, @PathVariable Long commentId) {
        return ApiResult.SUCCESS(commentDeleteService.deleteComment(commentId, user));
    }
}
