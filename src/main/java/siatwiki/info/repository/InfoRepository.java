package siatwiki.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import siatwiki.info.entity.Info;


@Repository
public interface InfoRepository  extends JpaRepository<Info, Integer>{

}

