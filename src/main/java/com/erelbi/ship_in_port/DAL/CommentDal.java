package com.erelbi.ship_in_port.DAL;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.erelbi.ship_in_port.Repository.CommentRepository;
import com.erelbi.ship_in_port.Repository.UserRepository;
import com.erelbi.ship_in_port.model.Comment;
import com.erelbi.ship_in_port.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDal {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment getCommentById(Long id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(id);
        return commentOptional.orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
    }

    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }

    public List<Comment> getCommentsByUserId(Long userId){

        Optional<User> userOptional = this.userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new EntityNotFoundException("User Not Found");
        } else {
            return this.commentRepository.getCommentsByUserId(userId);
        }        
    }

    public void deleteCommentById(Long id){
        this.commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Command Not Found"));
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    
}