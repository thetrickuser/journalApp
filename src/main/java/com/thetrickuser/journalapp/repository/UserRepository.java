package com.thetrickuser.journalapp.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thetrickuser.journalapp.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

  Optional<User> findByUsername(String username);
}
