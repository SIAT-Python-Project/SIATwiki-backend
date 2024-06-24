package com.webserver.siatwiki.info.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webserver.siatwiki.info.entity.Category;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.info.entity.QInfo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InfoQueryDslRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QInfo qInfo = QInfo.info;

    public InfoQueryDslRepository(JPAQueryFactory jpaQueryFactory) {
        super(Info.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Info> findAllByPersonId(Long personId) {
        return jpaQueryFactory
                .selectFrom(qInfo)
                .where(eqPersonId(personId))
                .leftJoin(qInfo.person)
                .fetchJoin()
                .orderBy(Category.queryDSLSortOption())
                .fetch();
    }

    private BooleanExpression eqPersonId(Long personId) {
        return qInfo.person.id.eq(personId);
    }
}
