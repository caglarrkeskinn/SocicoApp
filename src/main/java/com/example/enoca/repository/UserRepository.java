package com.example.enoca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enoca.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
