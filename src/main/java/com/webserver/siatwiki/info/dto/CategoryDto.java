package com.webserver.siatwiki.info.dto;

import lombok.Builder;
import lombok.Data;

public class CategoryDto {

    @Data
    @Builder
    public static class CategoryResponseDto {
        private int order;
        private String name;
        private String krName;
    }
}
