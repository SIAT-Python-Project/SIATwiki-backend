package com.webserver.siatwiki.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webserver.siatwiki.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}