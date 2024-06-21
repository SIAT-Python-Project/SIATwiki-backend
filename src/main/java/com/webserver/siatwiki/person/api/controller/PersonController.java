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
        Optional<Person> personOptional = personService.getPerson(id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            String filePath = null;
            if (person.getProfile() != null) {
                filePath = profileService.getProfileById(person.getProfile().getAttachmentFileNo());
            }

            PersonDTO.PersonResponseDTO responseDTO = personService.toDto(person);

            responseDTO.setFilePath(filePath);
            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.CONTENT_TYPE, "application/json");

            return new ResponseEntity<>(responseDTO, header, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/api/person")
    public ResponseEntity<PersonDTO.PersonResponseDTO> savePerson(@RequestPart PersonDTO.PersonRequestDTO person,
                                                                  @RequestPart MultipartFile file) {
        try {
            Long profileId = profileService.saveProfile(file);
            personService.savePerson(person, profileId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/api/person")
    public ResponseEntity<List<PersonDTO.PersonIdNameDTO>> getAllPersonIdAndNames() {
        List<PersonDTO.PersonIdNameDTO> response = personService.getAllPersonIdAndName();
        return ResponseEntity.ok(response);
    }
}
