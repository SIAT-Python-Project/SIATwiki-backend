package com.webserver.siatwiki.info.service;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.entity.Category;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.info.repository.InfoQueryDslRepository;
import com.webserver.siatwiki.info.repository.InfoRepository;
import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;
    private final PersonRepository personRepository;
    private final InfoQueryDslRepository infoQueryDslRepository;

    public InfoDto.InfoResponseDto createInfo(InfoDto.InfoRequestDto requestDto, Integer personId) throws IllegalArgumentException, NoSuchElementException {
        Person person = personRepository.findById(personId)
                .orElseThrow();

        Info info = dtoToEntity(requestDto, person);

        Info createInfo = infoRepository.save(info);

        return entityToDto(createInfo);
    }

    public List<InfoDto.InfoResponseDto> findAllByPersonId(int personId) throws NoSuchElementException {
        List<Info> infos = infoQueryDslRepository.findAllByPersonId(personId);

        return infos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
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
                .category(info.getType().getKr())
                .content(info.getContent())
                .updateDate(info.getUpdateDate())
                .build();
    }

    public InfoDto.InfoResponseDto getInfoById(Integer infoId) throws NoSuchElementException {
        Info info = infoRepository.findById(infoId)
                .orElseThrow();

        return entityToDto(info);
    }
}
