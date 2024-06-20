package com.webserver.siatwiki.person.service;

import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService{

    private final PersonRepository personRepository;
    private final UserRepository userRepository;


    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

    public void save(Person person){
        personRepository.save(person);
    }

    //DTO 받아서 Entity로 만들기
    public Person DtoToEntity(PersonDTO.PersonRequestDTO personRequestDTO) {

        //작성자 찾기
        User user = userRepository.findById(personRequestDTO.getUserId()).get();
        System.out.println(user);
        Person person = Person.builder()
                .user(user)
                .name(personRequestDTO.getName())
                .mbti(personRequestDTO.getMbti())
                .github(personRequestDTO.getGithub())
                .email(personRequestDTO.getEmail())
                .build();

        return person;
    }


}
