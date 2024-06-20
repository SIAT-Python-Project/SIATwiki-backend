package com.webserver.siatwiki.info.entity;

import com.webserver.siatwiki.common.util.converter.AbstractEnumNameConverter;
import com.webserver.siatwiki.common.util.converter.EnumName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category implements EnumName<String> {
    OUTLINE(0, "개요"),
    CAREER(1, "생애"),
    TECH_STACK(2, "기술 스택"),
    WANT(3, "하고 싶은 것"),
    ANALECTS(4, "어록"),
    CONTROVERSY(5, "논란"),
    DIGRESSION(6, "여담");
    private final int order;
    private final String kr;

    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Category, String> {
        public Converter() {
            super(Category.class);
        }
    }
}
