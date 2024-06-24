package com.webserver.siatwiki.person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PersonDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonRequestDTO {
        private String name;
        private String mbti;
        private String email;
        private String github;
        private Long userId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonResponseDTO {
        private Long id;
        private String name;
        private String mbti;
        private String email;
        private String github;
        private String userName;
        private String filePath;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonIdNameDTO{
        private Long id;
        private String name;
    }
}
