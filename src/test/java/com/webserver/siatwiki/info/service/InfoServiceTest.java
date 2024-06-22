package com.webserver.siatwiki.info.service;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InfoServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    InfoService infoService;

    @Test
    @Transactional
    void 삽입() {
        User user = User.builder()
                .email("jang010505@gmail.com")
                .role(Role.ADMIN)
                .name("장희권")
                .password("1234")
                .build();
        userRepository.save(user);

        Person person = Person.builder()
                .name("장희권")
                .email("jang010505@gmail.com")
                .mbti("ESTJ")
                .user(user)
                .build();
        personRepository.save(person);

        InfoDto.InfoRequestDto requestDto = new InfoDto.InfoRequestDto("OUTLINE", "ㅎ하하하하하ㅏ핳ㅎ", person.getId());

        InfoDto.InfoResponseDto responseDto = infoService.createInfo(requestDto);

        Assertions.assertEquals(responseDto.getContent(), requestDto.getContent());
    }

    @Test
    @Transactional
    void 유효하지_않는_카테고리() {
        User user = User.builder()
                .email("jang010505@gmail.com")
                .role(Role.ADMIN)
                .name("장희권")
                .password("1234")
                .build();
        userRepository.save(user);

        Person person = Person.builder()
                .name("장희권")
                .email("jang010505@gmail.com")
                .mbti("ESTJ")
                .user(user)
                .build();
        personRepository.save(person);

        InfoDto.InfoRequestDto requestDto = new InfoDto.InfoRequestDto("1231231", "ㅎ하하하하하ㅏ핳ㅎ", person.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            infoService.createInfo(requestDto);
        });
    }


    @Test
    @Transactional
    void 없는_person() {
        User user = User.builder()
                .email("jang010505@gmail.com")
                .role(Role.ADMIN)
                .name("장희권")
                .password("1234")
                .build();
        userRepository.save(user);

        Person person = Person.builder()
                .name("장희권")
                .email("jang010505@gmail.com")
                .mbti("ESTJ")
                .user(user)
                .build();
        personRepository.save(person);

        InfoDto.InfoRequestDto requestDto = new InfoDto.InfoRequestDto("OUTLINE", "ㅎ하하하하하ㅏ핳ㅎ", person.getId());

        assertThrows(NoSuchElementException.class, () -> {
            infoService.createInfo(requestDto);
        });
    }
}