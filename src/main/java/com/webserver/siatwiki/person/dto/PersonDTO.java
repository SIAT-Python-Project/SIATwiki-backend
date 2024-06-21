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
        private int userId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonResponseDTO {
        private int id;
        private String name;
        private String mbti;
        private String email;
        private String github;
        private String userName;  // Assuming User entity has a name field
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonIdNameDTO{
        private int id;
        private String name;
    }
}
