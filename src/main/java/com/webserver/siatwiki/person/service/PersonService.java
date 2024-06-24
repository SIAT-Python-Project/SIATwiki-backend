package com.webserver.siatwiki.person.service;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.info.entity.Category;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.info.repository.InfoRepository;
import com.webserver.siatwiki.person.dto.PersonDTO;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.repository.ProfileQueryDslRepository;
import com.webserver.siatwiki.profile.repository.ProfileRepository;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.webserver.siatwiki.common.response.error.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final InfoRepository infoRepository;
    private final ProfileRepository profileRepository;
    private final ProfileQueryDslRepository profileQueryDslRepository;

    @Transactional
    public Person savePerson(PersonDTO.PersonRequestDTO personRequestDTO, Long profileId) {
        Profile profile = null;
        if (profileId != null) {
            profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new CustomException(PROFILE_NOT_FOUND));
        }

        //user먼저 찾아주고 넣어줌
        User user = userRepository.findById(personRequestDTO.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Person person = toEntity(personRequestDTO, user, profile);

        Person newPerson = personRepository.save(person);

        for (Category category : Category.values()) {
            Info info = Info.builder()
                    .type(category)
                    .content("")
                    .person(person)
                    .build();

            infoRepository.save(info);
        }

        return newPerson;
    }

    @Transactional
    public Person getPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new CustomException(PERSON_NOT_FOUND));
    }

    @Transactional
    public PersonDTO.PersonResponseDTO updatePerson(Long id, PersonDTO.PersonRequestDTO personRequestDTO) {
        //연관관계 (유저)먼저 UserId가 유효한지 판단 후 필드값 업데이트
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new CustomException(PERSON_NOT_FOUND));

        User user = userRepository.findById(personRequestDTO.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        //엔터티에 setter대신 update메서드를 넣었음
        person.update(personRequestDTO.getName(), personRequestDTO.getMbti(),
                personRequestDTO.getEmail(), personRequestDTO.getGithub(), user);

        return toDto(personRepository.save(person));
    }

    @Transactional
    public void deletePerson(Long id) {
        Profile profile = profileQueryDslRepository.findByPersonId(id);
        personRepository.deleteById(id);

        if (profile != null) {
            deleteLocalFile(profile.getFilePath());
            profileRepository.delete(profile);
        }
    }

    //[{id:이름},{id:이름2},...]
    @Transactional
    public List<PersonDTO.PersonIdNameDTO> getAllPersonIdAndName() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                .map(person -> new PersonDTO.PersonIdNameDTO(person.getId(), person.getName()))
                .collect(Collectors.toList());
    }
    //DTO 변환 메서드

    public Person toEntity(PersonDTO.PersonRequestDTO personRequestDTO, User user, Profile profile) {
        return Person.builder()
                .user(user)
                .profile(profile)
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

    private void deleteLocalFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }
}
