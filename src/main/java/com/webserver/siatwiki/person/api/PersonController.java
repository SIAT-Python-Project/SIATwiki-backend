package com.webserver.siatwiki.person.api;

import com.webserver.siatwiki.common.util.cookie.CookieUtil;
import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import com.webserver.siatwiki.profile.service.ProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;
    private final ProfileService profileService;
    private final CookieUtil cookieUtil;

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
    public ResponseEntity<PersonDTO.PersonResponseDTO> savePerson(@RequestPart PersonDTO.PersonRequestDTO person, @RequestPart(required = false) MultipartFile file, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CREATED;
        Cookie[] cookies = request.getCookies();
        int userId = cookieUtil.decipherCookie(cookies);

        Long profileId = null;

        if (file != null) {
            profileId = profileService.saveProfile(file);
        }

        person.setUserId(userId);

        PersonDTO.PersonResponseDTO personResponseDTO = personService.toDto(personService.savePerson(person, profileId));
        HttpHeaders header = new HttpHeaders();

        return new ResponseEntity<>(personResponseDTO, header, status);
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
