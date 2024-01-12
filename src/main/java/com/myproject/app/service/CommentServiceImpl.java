package com.myproject.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.app.models.Comment;
import com.myproject.app.models.Post;
import com.myproject.app.models.User;
import com.myproject.app.repo.CommentRepository;
import com.myproject.app.repo.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment creatComment(Comment comment, Integer postId, Integer userId) throws Exception {
        
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

        Optional<Comment> opt = commentRepository.findById(commentId);
        if (opt.isEmpty()) {
            throw new Exception("comment does not found");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer commendId, Integer userId) throws Exception {
        
        Comment comment = findCommentById(commendId);
        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        } else {
            comment.getLiked().remove(user);
        }

        return commentRepository.save(comment);
    }
    
}
