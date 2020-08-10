package com.erelbi.ship_in_port.Repository;

import java.util.List;

import com.erelbi.ship_in_port.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    @Query(
        value = "SELECT * FROM comment WHERE user_id = ?1",
        nativeQuery = true)
    public List<Comment> getCommentsByUserId(Long userId);
    
}