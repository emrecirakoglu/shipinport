package com.erelbi.ship_in_port.Controller;

import java.util.List;

import com.erelbi.ship_in_port.DAL.PortDal;
import com.erelbi.ship_in_port.model.Port;

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
@RequestMapping(path = "/port")
public class PortController {
    
    @Autowired
    private PortDal portDal;

    @GetMapping(path = "/")
    public ResponseEntity<List<Port>> getAllPorts(){
        List<Port> allPorts = this.portDal.getAllPorts(); 
        return ResponseEntity.ok(allPorts);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Port> getPortById(@PathVariable Long id){
        Port port = this.portDal.getPortById(id);
        return ResponseEntity.ok(port);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.portDal.deletePortById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> savePort(@RequestBody Port port) {
        this.portDal.savePort(port);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}