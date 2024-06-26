package com.webserver.siatwiki.info.api;

import com.webserver.siatwiki.info.dto.InfoDto;
import com.webserver.siatwiki.info.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<InfoDto.InfoResponseDto> getInfoById(@PathVariable final Long infoId) {
        InfoDto.InfoResponseDto responseDto = infoService.getInfoById(infoId);;
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping("/api/info/person/{personId}")
    public ResponseEntity<List<InfoDto.InfoResponseDto>> getPersonInfo(@PathVariable("personId") final Long personId) {
        List<InfoDto.InfoResponseDto> responseDto = infoService.findAllByPersonId(personId);;
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(responseDto, status);
    }

    @PutMapping("/api/info/{infoId}")
    public ResponseEntity<InfoDto.InfoResponseDto> updateInfo(@PathVariable final Long infoId, @RequestBody final InfoDto.InfoRequestDto requestDto) {
        InfoDto.InfoResponseDto responseDto = infoService.updateInfo(infoId, requestDto);
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(responseDto, status);
    }
}
