package com.webserver.siatwiki.info.entity;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
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
    CONTROVERSY( 5, "논란"),
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

    public static OrderSpecifier<Integer> queryDSLSortOption() {
        CaseBuilder.Cases<Integer, Expression<Integer>> caseBuilder = new CaseBuilder.Cases<>(Integer.class) {
            @Override
            protected Expression<Integer> createResult(Class<? extends Integer> type, Expression<Integer> last) {
                return Expressions.operation(type, Ops.CASE, last);
            }
        };

        for (Category category : Category.values()) {
            caseBuilder = caseBuilder.when(QInfo.info.type.eq(category)).then(category.order);
        }

        Expression<Integer> caseExpression = caseBuilder.otherwise(Category.values().length);

        return new OrderSpecifier<>(Order.ASC, caseExpression);
    }
}
