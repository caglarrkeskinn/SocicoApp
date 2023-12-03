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

import com.example.enoca.entities.Comment;
import com.example.enoca.request.CommentCreateRequest;
import com.example.enoca.request.CommentUpdateRequest;
import com.example.enoca.service.CommentService;


@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId, Optional<Long> postId) {
		return commentService.getAllComments(userId, postId);
		
	}
	
	@PostMapping
	public Comment createComment(@RequestBody CommentCreateRequest commentRequest) {
		return commentService.createComment(commentRequest);
	}
	
	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable Long commentId) {
		return commentService.getCommentById(commentId);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentRequest) {
		return commentService.updateComment(commentId, commentRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable Long commentId) {
		commentService.deleteComment(commentId);
	}

}

