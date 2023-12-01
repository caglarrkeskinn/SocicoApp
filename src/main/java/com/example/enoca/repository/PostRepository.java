package com.example.enoca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enoca.entities.Post;



public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
