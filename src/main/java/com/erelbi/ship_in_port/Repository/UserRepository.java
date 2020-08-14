package com.erelbi.ship_in_port.Repository;

import java.util.List;

import com.erelbi.ship_in_port.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    
}