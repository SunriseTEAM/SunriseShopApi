package me.sunrise.shopapi.repository;

import me.sunrise.shopapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
