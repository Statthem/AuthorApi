package com.statthem.authorapi.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.statthem.authorapi.model.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, BigInteger> {

	@Query("select a from Author a where a.firstName = :firstName and a.secondName = :secondName")
	Author findByName(@Param("firstName") String name,  @Param("secondName") String secondName);
	

}

