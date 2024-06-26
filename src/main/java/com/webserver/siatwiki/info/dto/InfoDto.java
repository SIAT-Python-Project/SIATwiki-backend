package com.webserver.siatwiki.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class InfoDto {

    @Data
    @AllArgsConstructor
    public static class InfoRequestDto {
        private String type;
        private String content;
        private Long personId;
    }

    @Data
    @Builder
    public static class InfoResponseDto {
        private Long id;
        private String category;
        private String content;
        private LocalDateTime updateDate;
        private Long personId;
    }
}
