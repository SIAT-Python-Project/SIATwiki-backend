package com.webserver.siatwiki.info.service;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.entity.Category;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.info.repository.InfoRepository;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;
    private final PersonRepository personRepository;

    public InfoDto.InfoResponseDto createInfo(InfoDto.InfoRequestDto requestDto, Integer personId) throws IllegalArgumentException, NoSuchElementException {
        Person person = personRepository.findById(personId)
                .orElseThrow();

        Info info = dtoToEntity(requestDto, person);

        Info createInfo = infoRepository.save(info);

        return entityToDto(createInfo);
    }

    private Info dtoToEntity(InfoDto.InfoRequestDto requestDto, Person person) throws IllegalArgumentException {
        return Info.builder()
                .type(Category.valueOf(requestDto.getType()))
                .content(requestDto.getContent())
                .person(person)
                .build();
    }

    private InfoDto.InfoResponseDto entityToDto(Info info) {
        return InfoDto.InfoResponseDto.builder()
                .id(info.getId())
                .category(info.getType())
                .content(info.getContent())
                .updateDate(info.getUpdateDate())
                .build();
    }
}
