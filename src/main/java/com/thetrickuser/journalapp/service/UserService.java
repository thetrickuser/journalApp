package com.thetrickuser.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thetrickuser.journalapp.model.Role;
import com.thetrickuser.journalapp.model.User;
import com.thetrickuser.journalapp.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return repository.findAll();
  }

  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    return repository.save(user);
  }

  public Optional<User> findUserById(ObjectId id) {
    return repository.findById(id);
  }

  public void deleteUserById(ObjectId id) {
    repository.deleteById(id);
  }

  public User findByUsername(String username) {
    Optional<User> userInDB = repository.findByUsername(username);
    if (userInDB.isPresent()) {
      return userInDB.get();
    } else {
      return null;
    }
  }

}
