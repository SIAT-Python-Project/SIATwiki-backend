package com.webserver.siatwiki.user.entity;

import com.webserver.siatwiki.common.util.converter.AbstractEnumNameConverter;
import com.webserver.siatwiki.common.util.converter.EnumName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements EnumName<String> {
	ADMIN, USER;
	
    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Role, String> {
        public Converter() {
            super(Role.class);
        }
    }
}
