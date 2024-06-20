package com.webserver.siatwiki.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webserver.siatwiki.person.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository< Person, Integer> {

//    @Query("select p from Person p where p.id=:id")
    public Optional<Person> findById(@Param("id") Integer id);
}
