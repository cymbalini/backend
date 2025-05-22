package com.app.backend.service;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User findById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user, int id) {
        return userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }
}
