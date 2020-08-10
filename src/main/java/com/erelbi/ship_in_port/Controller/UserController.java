package com.erelbi.ship_in_port.Controller;

import java.util.List;

import com.erelbi.ship_in_port.DAL.UserDal;
import com.erelbi.ship_in_port.model.User;

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
@RequestMapping(path = "/user")
public class UserController {
    
    @Autowired
    private UserDal userDal;

    @GetMapping(path = "/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDal.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userDal.getUserByID(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userDal.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userDal.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}