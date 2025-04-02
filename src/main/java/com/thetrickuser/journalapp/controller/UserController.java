package com.thetrickuser.journalapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.thetrickuser.journalapp.model.User;
import com.thetrickuser.journalapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
      List<User> allUsers = userService.getAllUsers();
      return new ResponseEntity<>(allUsers, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User user) {
      User user2 = userService.saveUser(user);
      return new ResponseEntity<>(user2, HttpStatus.CREATED);
  }

  @PutMapping("/{username}")
  public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
    User userInDB = userService.findByUsername(username);
    if (userInDB != null) {
      userInDB.setUsername(user.getUsername());
      userInDB.setPassword(user.getPassword());
      userService.saveUser(userInDB);
      return new ResponseEntity<>(userInDB, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  

}
