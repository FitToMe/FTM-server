package com.FitToMe.project.Service.Comment.CommunityComment;

import com.FitToMe.project.DTO.Comment.CommunityCommentDTO;
import com.FitToMe.project.Repository.Comment.CommunityCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommunityCommentSearchService {
    private final CommunityCommentRepository commentRepository;

    public List<CommunityCommentDTO> findByCommunityPostId(Long communityPostId) {
        return commentRepository.findCommentsByCommunityPostId(communityPostId).stream().map(CommunityCommentDTO::new).collect(Collectors.toList());
    }

    public List<CommunityCommentDTO> findByUserId(Long userId) {
        return commentRepository.findCommentsByUserId(userId).stream().map(CommunityCommentDTO::new).collect(Collectors.toList());
    }
}
