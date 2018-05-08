package com.statthem.authorapi.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.authorapi.dao.AuthorDAO;
import com.statthem.authorapi.dto.AuthorDto;
import com.statthem.authorapi.model.Author;


@Service
public class AuthorService implements IAuthorService {
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Author getAuthorByID(BigInteger id) {
		Author author = null;
		
		author = authorDAO.findOne(id);
		
		return author;
	}

	@Override
	public Author getAuthorByName(String firstName, String secondName) {
		Author author = null;
		
		author = authorDAO.findByName(firstName, secondName);
		
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> allAuthorsList;
		
		allAuthorsList = authorDAO.findAll();
		
		return allAuthorsList;
		
	}

	@Override
	public boolean addNewAuthor(Author newAuthor) {
		boolean flag = true;
		
		try {
		authorDAO.saveAndFlush(newAuthor);
		}catch(Exception exc) {
			exc.printStackTrace(System.err);
			flag = false;
		}
	
		return flag;
	}

	@Override
	public boolean updateAuthor(Author updatedAuthor) {
		boolean flag = true;
		Author author = null; 
		
		try {
		author = authorDAO.findOne(updatedAuthor.getId());
		authorDAO.saveAndFlush(updatedAuthor);
		}catch(Exception exc) {
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean deleteAuthor(BigInteger id) {
		boolean flag = true;
		
		try {
		authorDAO.delete(id);
		}catch(Exception exc) {
			flag = false;
		}

		return flag;
	}
	
	public AuthorDto convertToDto(Author author) {
		AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
	    
	    return authorDto;
	}

	
	
	

}
