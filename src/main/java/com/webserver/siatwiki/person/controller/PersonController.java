package com.webserver.siatwiki.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PersonController {

    //객체를 JSON으로 넘겨주기 위한 객체
//    @Autowired
//    private ObjectMapper mapper;


    private final PersonService personService;

    @GetMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> getPerson(@PathVariable("personId") int id)  {
        //결과값이 없다면 404반환

        // getPerson은 Optional<Person>반환
        // personId의 PersonResponseDTO를 반환
        Optional<Person> personOptional = personService.getPerson(id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            System.out.println(person);
            PersonDTO.PersonResponseDTO responseDTO = new PersonDTO.PersonResponseDTO(person);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json"));

            return new ResponseEntity<>(responseDTO, header,HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
