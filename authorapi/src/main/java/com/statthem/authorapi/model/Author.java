package com.statthem.authorapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6434941038242516853L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private	BigInteger id;

	@Column
    private	String firstName;

	@Column
	private String secondName;

	@Column
	private String shortBiography;

	@Column
	private Date birhDay;

	public Author() {

	}

}
