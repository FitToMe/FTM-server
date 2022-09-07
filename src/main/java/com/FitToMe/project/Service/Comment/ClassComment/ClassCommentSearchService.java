package com.FitToMe.project.Service.Comment.ClassComment;

import com.FitToMe.project.DTO.Comment.ClassCommentDTO;
import com.FitToMe.project.Repository.Comment.ClassCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ClassCommentSearchService {
    private final ClassCommentRepository commentRepository;

    public List<ClassCommentDTO> findByClassPostId(Long classPostId) {
        return commentRepository.findCommentsByClassPostId(classPostId).stream().map(ClassCommentDTO::new).collect(Collectors.toList());
    }

    public List<ClassCommentDTO> findByUserId(Long userId) {
        return commentRepository.findCommentsByUserId(userId).stream().map(ClassCommentDTO::new).collect(Collectors.toList());
    }
}
