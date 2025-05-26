package com.example.quanlysach.service.comment;

import com.example.quanlysach.dto.comment.CommentRequest;
import com.example.quanlysach.dto.comment.CommentResponse;
import com.example.quanlysach.entity.Comment;
import com.example.quanlysach.entity.Post;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.repository.CommentRepository;
import com.example.quanlysach.repository.PostRepository;
import com.example.quanlysach.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse getCommentDetail(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return toDto(comment);
    }

    @Override
    public CommentResponse createComment(CommentRequest request, String username) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user)
                .build();
        return toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    private CommentResponse toDto(Comment comment) {
        CommentResponse dto = new CommentResponse();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUsername(comment.getUser().getUsername());
        return dto;
    }
}
