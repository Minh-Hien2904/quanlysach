package com.example.quanlysach.service.post;

import com.example.quanlysach.dto.post.PostRequestDTO;
import com.example.quanlysach.dto.post.PostResponseDTO;
import com.example.quanlysach.entity.Post;
import com.example.quanlysach.repository.PostRepository;
import com.example.quanlysach.service.post.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToResponse(post);
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setPublisher(request.getPublisher());
        postRepository.save(post);
        return mapToResponse(post);
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostRequestDTO request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setPublisher(request.getPublisher());
        return mapToResponse(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponseDTO likePost(Long id, boolean like) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (like) {
            post.setLikes(post.getLikes() + 1);
        } else {
            post.setDislikes(post.getDislikes() + 1);
        }
        return mapToResponse(postRepository.save(post));
    }

    private PostResponseDTO mapToResponse(Post post) {
        PostResponseDTO response = new PostResponseDTO();
        BeanUtils.copyProperties(post, response);
        return response;
    }
}

