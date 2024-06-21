package com.webserver.siatwiki.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webserver.siatwiki.person.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository< Person, Integer> {

    //Querydsl은 은 마지막에 한번에 바꿀게요,,ㅎ
    @Query("select p.name from Person p")
    List<String> findAllPersonNames();
}
