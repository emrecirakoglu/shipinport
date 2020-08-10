package com.erelbi.ship_in_port.Controller;

import java.util.List;

import com.erelbi.ship_in_port.DAL.CommentDal;
import com.erelbi.ship_in_port.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentDal commentDal;
    
    @PostMapping(path = "/")
    public ResponseEntity<Void> saveComment(@RequestBody Comment comment){
        commentDal.saveComment(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        List<Comment> comments = this.commentDal.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Comment comment = this.commentDal.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = this.commentDal.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.commentDal.deleteCommentById(id);
    }
}