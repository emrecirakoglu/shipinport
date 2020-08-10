package com.erelbi.ship_in_port.Repository;

import com.erelbi.ship_in_port.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}