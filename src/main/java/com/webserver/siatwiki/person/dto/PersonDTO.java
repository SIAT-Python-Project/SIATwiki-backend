package com.webserver.siatwiki.person.dto;

import com.webserver.siatwiki.person.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PersonDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonResponseDTO {

        //백엔드가 전해줄 때 만드는 DTO
        private int id;
        private String name;
        private String mbti;
        private String email;
        private String github;
        private String createDate;
        private String updateDate;
        private String userName; //작성자명

        //Entity to DTO
        public PersonResponseDTO(Person person) {
            this.id = person.getId();
            this.name = person.getName();
            this.mbti = person.getMbti();
            this.email = person.getEmail();
            this.github = person.getGithub();
            this.createDate = person.getCreateDate().toString();
            this.updateDate = person.getUpdateDate().toString();
            this.userName = person.getUser().getName();
        }



    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PersonRequestDTO {
        //프론트로부터 요청된 필드로 생성
        private String name;
        private String mbti;
        private String email;
        private String github;
        private int userId;

    }
}
