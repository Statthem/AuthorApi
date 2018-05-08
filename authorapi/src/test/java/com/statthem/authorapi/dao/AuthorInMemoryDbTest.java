package com.statthem.authorapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.authorapi.configuration.DataSourceConfiguration;
import com.statthem.authorapi.configuration.TestDataSourceConfiguration;
import com.statthem.authorapi.model.Author;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestDataSourceConfiguration.class },
                      loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AuthorInMemoryDbTest {

	@Resource
	private AuthorDAO authorDao;

	@Test
	public void givenAuthor_whenSave_thenGetOk() {
		
		Author author = new Author();
		author.setFirstName("Alex");
		author.setSecondName("Lit");
		author.setShortBiography("Was born to be cool");
		new Date(19990324);
		author.setBirhDay(Date.valueOf("1999-03-24"));
		authorDao.saveAndFlush(author);

		Author newAuthor = authorDao.findOne(BigInteger.ONE);
		assertEquals("Alex", newAuthor.getFirstName());
	}
	
	@Test
	public void given2Authors_whenSave_thenGetOk() {
		
		Author author1 = new Author();
		author1.setFirstName("Alexis");
		author1.setSecondName("Lit");
		author1.setShortBiography("Was born to be cool");
		new Date(19990324);
		author1.setBirhDay(Date.valueOf("1999-03-24"));
		authorDao.saveAndFlush(author1);
		
		Author author2 = new Author();
		author2.setFirstName("Hota");
		author2.setSecondName("Litt");
		author2.setShortBiography("Was born to be cooler");
		author2.setBirhDay(new Date(19990324).valueOf("1999-03-24"));
		authorDao.saveAndFlush(author2);

		List<Author> allAuthors = authorDao.findAll();
		assertNotNull(allAuthors);
		assertEquals("Hota",allAuthors.get(1).getFirstName());
	}
	
	@Test
	public void givenAuthor_whenUpdate_thenGetOk() {
		
		Author author = new Author();
		author.setFirstName("Alexey");
		author.setSecondName("Lit");
		author.setShortBiography("Was born to be cool");
		new Date(19990324);
		author.setBirhDay(Date.valueOf("1999-03-24"));
		authorDao.saveAndFlush(author);
		
		author = authorDao.findOne(BigInteger.valueOf(3));
		author.setFirstName("Vetal");

		Author newAuthor = authorDao.findOne(author.getId());
		assertEquals("Vetal", newAuthor.getFirstName());
	}
	
	@Test
	public void givenAuthor_whenDelete_thenGetNull() {
		
		Author author = new Author();
		author.setFirstName("Retro");
		author.setSecondName("Rat");
		author.setShortBiography("Was born to be cool");
		new Date(19990324);
		author.setBirhDay(Date.valueOf("1999-03-24"));
		authorDao.saveAndFlush(author);
		
//		author = authorDao.findOne(BigInteger.valueOf(4));
//		assertEquals("Retro", author.getFirstName());
		
		authorDao.delete(author);
		Author newAuthor = authorDao.findOne(BigInteger.valueOf(4));
		assertNull(newAuthor);
	}
		
	@Test
	public void getAuthorByName_test() {
		
		Author author = new Author();
		author.setFirstName("Oda");
		author.setSecondName("Nobunaga");
		author.setShortBiography("Was born to be cool");
		new Date(19990324);
		author.setBirhDay(Date.valueOf("1999-03-24"));
		authorDao.saveAndFlush(author);

		Author newAuthor = authorDao.findByName(author.getFirstName(), author.getSecondName());
		assertEquals("Oda", newAuthor.getFirstName());
		assertEquals("Nobunaga", newAuthor.getSecondName());
	}

}
