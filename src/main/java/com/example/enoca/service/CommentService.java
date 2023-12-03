package com.example.enoca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.enoca.entities.Comment;
import com.example.enoca.entities.Post;
import com.example.enoca.entities.User;
import com.example.enoca.repository.CommentRepository;
import com.example.enoca.request.CommentCreateRequest;
import com.example.enoca.request.CommentUpdateRequest;


@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;
	
	public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {

		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}else
			return commentRepository.findAll();
	}

	public Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createComment(CommentCreateRequest commentRequest) {
		User user = userService.getById(commentRequest.getUserId());
		Post post = postService.getPostById(commentRequest.getPostId());
		if(user!=null && post!=null) {
			Comment comment = new Comment();
			comment.setId(commentRequest.getId());
			comment.setText(commentRequest.getText());
			comment.setUser(user);
			comment.setPost(post);
			return commentRepository.save(comment);
		}else
			return null;
	
	}

	public Comment updateComment(Long commentId, CommentUpdateRequest commentRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment newComment = comment.get();
			newComment.setText(comment.get().getText());
			return commentRepository.save(newComment);
		}else
			return null;
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}
}

