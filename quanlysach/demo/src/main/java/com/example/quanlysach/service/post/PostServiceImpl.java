package com.example.quanlysach.service.post;

import com.example.quanlysach.dto.request.PostRequest;
import com.example.quanlysach.dto.response.PostResponse;
import com.example.quanlysach.entity.Post;
import com.example.quanlysach.mapper.PostMapper;
import com.example.quanlysach.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.toDTO(post);
    }

    @Override
    public PostResponse createPost(PostRequest request) {
        Post post = PostMapper.toEntity(request);
        Post saved = postRepository.save(post);
        return PostMapper.toDTO(saved);
    }

    @Override
    public PostResponse updatePost(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostMapper.updateEntity(post, request);
        Post updated = postRepository.save(post);
        return PostMapper.toDTO(updated);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse likePost(Long id, boolean like) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (like) {
            post.setLikes(post.getLikes() + 1);
        } else {
            post.setDislikes(post.getDislikes() + 1);
        }
        Post updated = postRepository.save(post);
        return PostMapper.toDTO(updated);
    }
}
