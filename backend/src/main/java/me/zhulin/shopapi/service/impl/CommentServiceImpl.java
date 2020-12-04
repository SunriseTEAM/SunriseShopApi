package me.zhulin.shopapi.service.impl;

import me.zhulin.shopapi.entity.Comment;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.repository.CommentRepository;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.CommentService;
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
