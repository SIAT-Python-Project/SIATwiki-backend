package com.webserver.siatwiki.common.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.PersistenceContext;;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class QueryDSLConfiguration {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}