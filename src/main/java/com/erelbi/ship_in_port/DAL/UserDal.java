package com.erelbi.ship_in_port.DAL;

import java.util.List;
import java.util.Optional;

import com.erelbi.ship_in_port.exeption.EntityNotFoundExeption;
import com.erelbi.ship_in_port.Repository.UserRepository;
import com.erelbi.ship_in_port.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDal {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new EntityNotFoundExeption("User not found"));
    }

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    public void deleteUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()){
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundExeption("User not found");
        }
    }
}