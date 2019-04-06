package com.tech.bahera.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.bahera.dto.User;

@Repository
public interface UserDAO extends MongoRepository<User, String>{

/*	User findOne(String id);*/
}
