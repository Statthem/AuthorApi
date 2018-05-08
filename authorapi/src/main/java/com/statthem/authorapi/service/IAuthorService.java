package com.statthem.authorapi.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.statthem.authorapi.dto.AuthorDto;
import com.statthem.authorapi.model.Author;

@Service
public interface IAuthorService {
	
	public Author getAuthorByID(BigInteger id);
	
	public Author getAuthorByName(String firstName, String SecondName);
	
	public List<Author> getAllAuthors();
	
	public boolean addNewAuthor(Author newAuthor);
	
	public boolean updateAuthor(Author updatedAuthor);
	
	public boolean deleteAuthor(BigInteger id);
	
	
	public AuthorDto convertToDto(Author author);

}
