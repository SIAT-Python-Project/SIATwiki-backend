package com.webserver.siatwiki.person.service;

import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

}
