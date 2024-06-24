package com.webserver.siatwiki.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webserver.siatwiki.person.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository< Person, Integer> {
}
