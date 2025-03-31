package com.thetrickuser.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thetrickuser.journalapp.model.User;
import com.thetrickuser.journalapp.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers() {
    return repository.findAll();
  }

  public User saveUser(User user) {
    return repository.save(user);
  }

  public Optional<User> findUserById(ObjectId id) {
    return repository.findById(id);
  }

  public void deleteUserById(ObjectId id) {
    repository.deleteById(id);
  }

  public User findByUsername(String username) {
    return repository.findByUsername(username);
  }

}
