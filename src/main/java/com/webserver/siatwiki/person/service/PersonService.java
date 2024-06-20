package com.webserver.siatwiki.person.service;

import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Transactional
    public void savePerson(PersonDTO.PersonRequestDTO personRequestDTO) {
        //user먼저 찾아주고 넣어줌
        User user = userRepository.findById(personRequestDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User가 존재하지 않습니다."));
        Person person = toEntity(personRequestDTO, user);
        personRepository.save(person);
    }

    @Transactional
    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person updatePerson(int id, PersonDTO.PersonRequestDTO personRequestDTO) {
        //연관관계 (유저)먼저 UserId가 유효한지 판단 후 필드값 업데이트
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();

            User user = userRepository.findById(personRequestDTO.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User가 존재하지 않습니다."));

            //엔터티에 setter대신 update메서드를 넣었음

            person.update(personRequestDTO.getName(), personRequestDTO.getMbti(),
                    personRequestDTO.getEmail(), personRequestDTO.getGithub(), user);
            return personRepository.save(person);
        } else {
            throw new NoSuchElementException("Person이 존재하지 않습니다.");
        }
    }



    //DTO 변환 메서드

    public Person toEntity(PersonDTO.PersonRequestDTO personRequestDTO, User user) {
        return Person.builder()
                .user(user)
                .name(personRequestDTO.getName())
                .mbti(personRequestDTO.getMbti())
                .email(personRequestDTO.getEmail())
                .github(personRequestDTO.getGithub())
                .build();
    }

    public PersonDTO.PersonResponseDTO toDto(Person person) {
        return PersonDTO.PersonResponseDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .mbti(person.getMbti())
                .email(person.getEmail())
                .github(person.getGithub())
                .userName(person.getUser().getName())  // Assuming User entity has a name field
                .build();
    }
}
