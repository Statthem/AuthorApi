package com.statthem.authorapi.restcontroller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.statthem.authorapi.model.Author;
import com.statthem.authorapi.service.IAuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	private static final Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	
	@Autowired
	IAuthorService authorService;
	
	
	 @RequestMapping(value = "/add",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseEntity<Object> saveAuthor(//@RequestBody Author newAuthor1,
	                                                 UriComponentsBuilder builder,
	                                                 HttpServletRequest request){
	        HttpHeaders httpHeaders = new HttpHeaders();

	       Author newAuthor = new Author();
	       newAuthor.setFirstName("Tester");
	       newAuthor.setSecondName("Testerowich");
           newAuthor.setShortBiography("hahahahahahaha");
           newAuthor.setBirhDay(Date.valueOf("1999-03-24"));
           
	     boolean flag = authorService.addNewAuthor(newAuthor);
	     if(flag==true)
	       logger.info("new author added");

	        httpHeaders.setLocation(builder.path("/").buildAndExpand().toUri());
	        return new ResponseEntity<>(newAuthor, httpHeaders, HttpStatus.CREATED);
	}
	 
}
	 
	 
	
