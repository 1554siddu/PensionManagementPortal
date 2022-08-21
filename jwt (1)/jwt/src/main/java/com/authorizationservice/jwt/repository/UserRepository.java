package com.authorizationservice.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorizationservice.jwt.model.User;



/**
 * Repository for User
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}