package com.webserver.siatwiki.person.api.controller;

import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.repository.ProfileRepository;
import com.webserver.siatwiki.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;
    private final ProfileService profileService;

    @GetMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> getPerson(@PathVariable("personId") int id) {
        HttpStatus status = HttpStatus.OK;
        Person person = personService.getPerson(id);
        String filePath = null;

        if (person.getProfile() != null) {
            filePath = profileService.getProfileUrlByPersonId(id);
        }

        PersonDTO.PersonResponseDTO responseDTO = personService.toDto(person);
        responseDTO.setFilePath(filePath);

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(responseDTO, header, status);
    }

    @PostMapping("/api/person")
    public ResponseEntity<Void> savePerson(@RequestPart PersonDTO.PersonRequestDTO person, @RequestPart(required = false) MultipartFile file) {
        HttpStatus status = HttpStatus.CREATED;
        Long profileId = null;

        if (file != null) {
            profileId = profileService.saveProfile(file);
        }

        personService.savePerson(person, profileId);

        return new ResponseEntity<>(status);
    }

    @PutMapping("/api/person/{personId}")
    public ResponseEntity<PersonDTO.PersonResponseDTO> updatePerson(@PathVariable("personId") int id,
                                                                    @RequestPart PersonDTO.PersonRequestDTO person,
                                                                    @RequestPart(required = false) MultipartFile file) {
        HttpStatus status = HttpStatus.CREATED;
        String filePath = profileService.updateProfile(id, file);
        PersonDTO.PersonResponseDTO responseDTO = personService.updatePerson(id, person);
        responseDTO.setFilePath(filePath);

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(responseDTO, header, status);
    }

    @DeleteMapping("/api/person/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") int id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        personService.deletePerson(id);
        return new ResponseEntity<>(status);
    }

    @GetMapping("/api/person")
    public ResponseEntity<List<PersonDTO.PersonIdNameDTO>> getAllPersonIdAndNames() {
        HttpStatus status = HttpStatus.OK;
        List<PersonDTO.PersonIdNameDTO> response = personService.getAllPersonIdAndName();
        return new ResponseEntity<>(response, status);
    }
}
