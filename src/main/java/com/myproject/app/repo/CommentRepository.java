package com.myproject.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.app.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
