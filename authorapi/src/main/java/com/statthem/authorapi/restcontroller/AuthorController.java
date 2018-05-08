package com.statthem.authorapi.restcontroller;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.statthem.authorapi.dto.AuthorDto;
import com.statthem.authorapi.model.Author;
import com.statthem.authorapi.service.IAuthorService;

@RestController("AuthorController")
@RequestMapping("/authors")
public class AuthorController {

	private static final Logger logger = LogManager.getLogger(AuthorController.class);
	private static final Logger jsonLogger = LogManager.getLogger("CONSOLE_JSON_APPENDER");

	@Autowired
	IAuthorService authorService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> saveAuthor(@RequestBody Author newAuthor, UriComponentsBuilder builder,
			HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (newAuthor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean flag = authorService.addNewAuthor(newAuthor);
		if (flag == true) {
			logger.info("new author added");
		}else {
		
		}

		AuthorDto authorDto = authorService.convertToDto(newAuthor);
		
		httpHeaders.setLocation(builder.path("/").buildAndExpand().toUri());
		return new ResponseEntity<>(authorDto, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAuthor(@RequestBody Author updatedAuthor, UriComponentsBuilder builder,
			HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (updatedAuthor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean flag = authorService.updateAuthor(updatedAuthor);
		if (flag == true) {
			logger.info("author with id =" + updatedAuthor + " updated");
		}else {
		
		}
		
		AuthorDto authorDto = authorService.convertToDto(updatedAuthor);

		httpHeaders.setLocation(builder.path("/").buildAndExpand().toUri());
		return new ResponseEntity<>(authorDto, httpHeaders, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/get/{authorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAuthor(@PathVariable Long authorId, UriComponentsBuilder builder,
			HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (authorId <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		 Author author = authorService.getAuthorByID(BigInteger.valueOf(authorId));
		
		if (author == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
		
		}

		httpHeaders.setLocation(builder.path("/").build().toUri());
		return new ResponseEntity<>(author, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllAuthors(UriComponentsBuilder builder,
			HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();

		

		 List<Author> authors = authorService.getAllAuthors();
		
		if (authors == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
		
		}

		httpHeaders.setLocation(builder.path("/").build().toUri());
		return new ResponseEntity<>(authors, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{authorId}", method = RequestMethod.GET)
	public ResponseEntity<Object> removeAuthor(@PathVariable Long authorId,UriComponentsBuilder builder,
			HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();
		
		if (authorId <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
            authorService.deleteAuthor(BigInteger.valueOf(authorId));
		
		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

	
}
