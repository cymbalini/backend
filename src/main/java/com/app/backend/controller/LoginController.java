package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "logged";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.findById(id);
        if(user != null)
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allusers")
    public ResponseEntity< List<User>> allUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/createuser")
    public void createUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PutMapping("/updateuser/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable int id){
        User updatedUser = userService.updateUser(user, id);
        if(updatedUser != null)
            return new ResponseEntity<>("updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("unable to update", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        User user = userService.findById(id);
        if(user != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }else
            return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String keyword){
        List<User> users = userService.searchUsers(keyword);
        if(users != null) {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
