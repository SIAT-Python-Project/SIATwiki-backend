package com.webserver.siatwiki.info.api;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @PostMapping("/api/info")
    public ResponseEntity<InfoDto.InfoResponseDto> createInfo(@RequestBody final InfoDto.InfoRequestDto requestDto) {

        InfoDto.InfoResponseDto responseDto = null;
        HttpStatus status = HttpStatus.CREATED;

        try {
            responseDto = infoService.createInfo(requestDto, requestDto.getPersonId());
        } catch (IllegalArgumentException e) {
            status = HttpStatus.BAD_REQUEST;
        } catch (NoSuchElementException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping("/api/info/{infoId}")
    public ResponseEntity<InfoDto.InfoResponseDto> getInfoById(@PathVariable final Integer infoId) {
        InfoDto.InfoResponseDto responseDto = null;
        HttpStatus status = HttpStatus.OK;

        try {
            responseDto = infoService.getInfoById(infoId);
        } catch (NoSuchElementException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping("/api/info/person/{personId}")
    public ResponseEntity<List<InfoDto.InfoResponseDto>> getPersonInfo(@PathVariable("personId") final int personId) {
        List<InfoDto.InfoResponseDto> responseDtos = null;
        HttpStatus status = HttpStatus.OK;

        try {
            responseDtos = infoService.findAllByPersonId(personId);
        } catch (NoSuchElementException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(responseDtos, status);
    }
}
