package me.zhulin.shopapi.repository;

import me.zhulin.shopapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
