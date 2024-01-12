package com.myproject.app.service;

import com.myproject.app.models.Comment;

public interface CommentService {
    
    public Comment creatComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer commendId, Integer userId) throws Exception;
}
