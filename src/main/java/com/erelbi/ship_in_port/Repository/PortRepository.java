package com.erelbi.ship_in_port.Repository;

import com.erelbi.ship_in_port.model.Port;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port,Long> {
    
}