package com.webserver.siatwiki.info.api;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.entity.Info;
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
        InfoDto.InfoResponseDto responseDto = infoService.createInfo(requestDto);
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping("/api/info/{infoId}")
    public ResponseEntity<InfoDto.InfoResponseDto> getInfoById(@PathVariable final Integer infoId) {
        InfoDto.InfoResponseDto responseDto = infoService.getInfoById(infoId);;
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping("/api/info/person/{personId}")
    public ResponseEntity<List<InfoDto.InfoResponseDto>> getPersonInfo(@PathVariable("personId") final int personId) {
        List<InfoDto.InfoResponseDto> responseDto = infoService.findAllByPersonId(personId);;
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(responseDto, status);
    }

    @PutMapping("/api/info/{infoId}")
    public ResponseEntity<InfoDto.InfoResponseDto> updateInfo(@PathVariable final Integer infoId, @RequestBody final InfoDto.InfoRequestDto requestDto) {
        InfoDto.InfoResponseDto responseDto = infoService.updateInfo(infoId, requestDto);
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(responseDto, status);
    }
}
