package com.example.enoca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.enoca.entities.Post;
import com.example.enoca.entities.User;
import com.example.enoca.repository.PostRepository;
import com.example.enoca.request.PostCreateRequest;
import com.example.enoca.request.PostUpdateRequest;
import com.example.enoca.response.PostResponse;


@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository , UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}
		list = postRepository.findAll();
		return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
	}

	public Post getPostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest postRequest) {
		User user = userService.getById(postRequest.getUserId());
		
		if(user == null) {
			return null;
		}
		Post post = new Post();
		post.setId(postRequest.getId());
		post.setText(postRequest.getText());
		post.setTitle(postRequest.getTitle());
		post.setUser(user);
		return postRepository.save(post);
	}

	public Post updatePostById(Long postId, PostUpdateRequest postRequest) {
		Optional <Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post updatedPost = post.get();
			updatedPost.setText(postRequest.getText());
			updatedPost.setTitle(postRequest.getTitle());
			postRepository.save(updatedPost);
			return updatedPost;
		}else
			return null; 
	}

	public void deleteById(Long postId) {
		postRepository.deleteById(postId);		
	}



}

