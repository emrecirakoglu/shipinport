package com.erelbi.ship_in_port.DAL;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.erelbi.ship_in_port.Repository.PortRepository;
import com.erelbi.ship_in_port.model.Port;
import com.erelbi.ship_in_port.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortDal {

    @Autowired
    private PortRepository portRepository;

    public List<Port> getAllPorts() {
        return this.portRepository.findAll();
    }

    public Port getPortById(Long id) {
        Optional<Port> portOptional = this.portRepository.findById(id);
        return portOptional.orElseThrow(() -> new EntityNotFoundException("Port Not Found"));
    }

    public void savePort(Port port) {
        this.portRepository.save(port);
    }

    public void deletePortById(Long id) {

        Port port = this.getPortById(id);
        List<User> visitedUsers = port.getVisitedUsers();

        for (User user : visitedUsers) {
            user.getVisitedPorts().remove(port);
        }

        this.portRepository.deleteById(id);   
    }
    
}