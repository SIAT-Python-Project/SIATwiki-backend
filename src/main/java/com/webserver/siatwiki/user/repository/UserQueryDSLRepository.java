package com.webserver.siatwiki.user.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webserver.siatwiki.user.entity.QUser;
import com.webserver.siatwiki.user.entity.User;

@Repository
public class UserQueryDSLRepository extends QuerydslRepositorySupport {

	private final JPAQueryFactory jpaQueryFactory;
	private final QUser qUser = QUser.user;

	public UserQueryDSLRepository(JPAQueryFactory jpaQueryFactory) {
		super(User.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public User findByEmail(String email) {
		return jpaQueryFactory.selectFrom(qUser).leftJoin(qUser.persons).fetchJoin().where(qUser.email.eq(email))
				.fetchOne();
	}

	public User findIdAndNameAndEmailByEmail(String email) {
		return jpaQueryFactory.selectFrom(qUser).leftJoin(qUser.persons).fetchJoin().where(qUser.email.eq(email))
				.fetchOne();
	}
}