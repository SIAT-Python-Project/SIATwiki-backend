package com.webserver.siatwiki.person.controller;

import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;

    @GetMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> getPerson(@PathVariable("personId") int id) {

        // getPerson은 Optional<Person>반환
        // personId의 PersonResponseDTO를 반환
        Optional<Person> personOptional = personService.getPerson(id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            System.out.println(person);
            PersonDTO.PersonResponseDTO responseDTO = new PersonDTO.PersonResponseDTO(person);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
