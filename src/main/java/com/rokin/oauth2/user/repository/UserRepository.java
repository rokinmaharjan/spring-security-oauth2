package com.rokin.oauth2.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rokin.oauth2.user.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

}
