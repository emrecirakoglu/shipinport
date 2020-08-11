package com.erelbi.ship_in_port.DAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erelbi.ship_in_port.exeption.EntityNotFoundExeption;
import com.erelbi.ship_in_port.Repository.PortRepository;
import com.erelbi.ship_in_port.Repository.UserRepository;
import com.erelbi.ship_in_port.model.Port;
import com.erelbi.ship_in_port.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDal {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortRepository portRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new EntityNotFoundExeption("User not found"));
    }

    public void saveUser(User user){

        if (user.getId() != null){
            this.updateUser(user);
        } else {
            userRepository.save(user);
        }
        
    }

    public void deleteUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()){
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundExeption("User not found");
        }
    }

    public void updateUser(User user){
        List<Port> ports = user.getVisitedPorts();
        List<Port> portsSetted = new ArrayList<>();

        if (ports.isEmpty()) {
            this.userRepository.save(user);
        } else {
            for (Port port : ports) {
                portsSetted.add(this.portRepository.getOne(port.getId())) ;
            }
        }

        user.setVisitedPorts(portsSetted);
        this.userRepository.save(user);

    }
}