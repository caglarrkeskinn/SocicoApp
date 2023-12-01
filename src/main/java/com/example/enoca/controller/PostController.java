package com.example.enoca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.enoca.entities.Post;
import com.example.enoca.request.PostCreateRequest;
import com.example.enoca.request.PostUpdateRequest;
import com.example.enoca.response.PostResponse;
import com.example.enoca.service.PostService;


@RestController
@RequestMapping("/posts")
public class PostController {
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
		return postService.getAllPosts(userId);
	}
	
	@PostMapping
	public Post createPost(@RequestBody PostCreateRequest postRequest) {
		return postService.createOnePost(postRequest);
		
	}
	
	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable Long postId) {
		return postService.getPostById(postId);
	}
	
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postRequest) {
		return postService.updatePostById(postId, postRequest);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deleteById(postId);
	}
}

