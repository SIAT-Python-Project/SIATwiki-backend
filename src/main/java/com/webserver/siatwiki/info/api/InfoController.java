package com.webserver.siatwiki.info.api;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
