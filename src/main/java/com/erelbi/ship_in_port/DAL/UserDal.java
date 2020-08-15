package com.erelbi.ship_in_port.DAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
        return userOptional.orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void saveUser(User user){

        if (user.getId() == null){
            userRepository.save(user);
        } else {
            this.updateUser(user);
        }
        
    }

    public void deleteUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()){
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public void updateUser(User user){

        if ( this.checkEmailValidation(user) ){
            List<Port> ports = user.getVisitedPorts();
            if (!ports.isEmpty()) {
                List<Port> portsSetted = new ArrayList<>();
                for (Port port : ports) {
                    portsSetted.add(this.portRepository.getOne(port.getId())) ;
                }
                user.setVisitedPorts(portsSetted);
            }
            this.userRepository.save(user);
        } else {
            throw new EntityExistsException("This e-mail address is registered in the system.");
        }

    }

    public Boolean checkUserExists(User user){
        // Check user register before. Not allow user to create with same email address. 
        String userMail = user.getEmail();
        if (userRepository.existsByEmail(userMail)) {
            throw new EntityExistsException("User already created before!");
        } else {
            return false;
        }
    }

    public Boolean checkEmailValidation(User user){   
        // When user attempt to update his mail, check new email using from anohter user.     
        List<User> users = this.userRepository.findByEmail(user.getEmail());
        for (User user2 : users) {
            if(user.getId() != user2.getId()){
                return false;
            }
        }
        return true;
    }
}