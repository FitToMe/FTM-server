package com.FitToMe.project.Service.Comment.SmallGroupComment;

import com.FitToMe.project.DTO.Comment.SmallGroupCommentDTO;
import com.FitToMe.project.Repository.Comment.SmallGroupCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SmallGroupCommentSearchService {
    private final SmallGroupCommentRepository commentRepository;

    public List<SmallGroupCommentDTO> findBySmallGroupId(Long smallGroupPostId) {
        return commentRepository.findCommentsBySmallGroupPostId(smallGroupPostId).stream().map(SmallGroupCommentDTO::new).collect(Collectors.toList());
    }

    public List<SmallGroupCommentDTO> findByUserId(Long userId) {
        return commentRepository.findCommentsByUserId(userId).stream().map(SmallGroupCommentDTO::new).collect(Collectors.toList());
    }
}
