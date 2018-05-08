package com.statthem.authorapi.dto;


import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorDto {
	
	
	private String firstName;

	
	private String secondName;

	
	private String shortBiography;

	
	private Date birhDay;

}
