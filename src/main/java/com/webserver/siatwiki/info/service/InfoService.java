package com.webserver.siatwiki.info.service;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.common.response.error.ErrorCode;
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

import static com.webserver.siatwiki.common.response.error.ErrorCode.CATEGORY_NAME_NOT_FOUND;
import static com.webserver.siatwiki.common.response.error.ErrorCode.INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;
    private final PersonRepository personRepository;
    private final InfoQueryDslRepository infoQueryDslRepository;

    public InfoDto.InfoResponseDto createInfo(InfoDto.InfoRequestDto requestDto) {
        Person person = personRepository.findById(requestDto.getPersonId())
                .orElseThrow(() -> new CustomException(ErrorCode.PERSON_NOT_FOUND));

        Info info = dtoToEntity(requestDto, person);

        Info createInfo = infoRepository.save(info);

        return entityToDto(createInfo);
    }

    public List<InfoDto.InfoResponseDto> findAllByPersonId(int personId) {
        List<Info> infos = infoQueryDslRepository.findAllByPersonId(personId);

        return infos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public InfoDto.InfoResponseDto getInfoById(Integer infoId) {
        Info info = infoRepository.findById(infoId)
                .orElseThrow(() -> new CustomException(ErrorCode.INFO_NOT_FOUND));

        return entityToDto(info);
    }

    public InfoDto.InfoResponseDto updateInfo(Integer infoId, InfoDto.InfoRequestDto dto) throws NoSuchElementException {
        Info target = infoRepository.findById(infoId)
                .orElseThrow(() -> new CustomException(INFO_NOT_FOUND));

        target.fetch(dto);

        Info updateInfo = infoRepository.save(target);

        return entityToDto(updateInfo);
    }

    private Info dtoToEntity(InfoDto.InfoRequestDto requestDto, Person person) {
        try {
            return Info.builder()
                    .type(Category.valueOf(requestDto.getType()))
                    .content(requestDto.getContent())
                    .person(person)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(CATEGORY_NAME_NOT_FOUND);
        }
    }

    private InfoDto.InfoResponseDto entityToDto(Info info) {
        return InfoDto.InfoResponseDto.builder()
                .id(info.getId())
                .category(info.getType().getKr())
                .content(info.getContent())
                .updateDate(info.getUpdateDate())
                .personId(info.getPerson().getId())
                .build();
    }
}
