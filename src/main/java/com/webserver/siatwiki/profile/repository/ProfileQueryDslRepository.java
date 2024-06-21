package com.webserver.siatwiki.profile.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.profile.entity.Profile;
import com.webserver.siatwiki.profile.entity.QProfile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileQueryDslRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProfile qProfile = QProfile.profile;

    public ProfileQueryDslRepository(JPAQueryFactory jpaQueryFactory) {
        super(Profile.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Profile findByPersonId(Integer personId) {
        return jpaQueryFactory
                .selectFrom(qProfile)
                .where(eqPersonId(personId))
                .fetchOne();
    }

    private BooleanExpression eqPersonId(int personId) {
        return qProfile.person.id.eq(personId);
    }
}