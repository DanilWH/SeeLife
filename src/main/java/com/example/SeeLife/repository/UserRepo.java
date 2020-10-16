package com.example.SeeLife.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.User;

public interface UserRepo extends CrudRepository<User, Long> {
    
    @Override
    List<User> findAll();
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    
}
