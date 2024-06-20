package com.webserver.siatwiki.person.api.controller;

import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;

    @GetMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> getPerson(@PathVariable("personId") int id) {
        Optional<Person> personOptional = personService.getPerson(id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            PersonDTO.PersonResponseDTO responseDTO = personService.toDto(person);

            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.CONTENT_TYPE, "application/json");

            return new ResponseEntity<>(responseDTO, header, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/api/person")
    public ResponseEntity<PersonDTO.PersonResponseDTO> savePerson(@RequestBody PersonDTO.PersonRequestDTO personRequestDTO) {
        try {
            personService.savePerson(personRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> updatePerson(@PathVariable("personId") int id,
                                                                    @RequestBody PersonDTO.PersonRequestDTO personRequestDTO) {
        try {
            Person updatedPerson = personService.updatePerson(id, personRequestDTO);
            PersonDTO.PersonResponseDTO responseDTO = personService.toDto(updatedPerson);

            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.CONTENT_TYPE, "application/json");

            return new ResponseEntity<>(responseDTO, header, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/api/person/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") int id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
