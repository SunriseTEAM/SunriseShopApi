package me.sunrise.shopapi.service.impl;

import me.sunrise.shopapi.entity.Comment;
import me.sunrise.shopapi.repository.CommentRepository;
import me.sunrise.shopapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
