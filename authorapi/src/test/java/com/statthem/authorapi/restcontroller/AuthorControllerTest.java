package com.statthem.authorapi.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorControllerTest {
	
		@Autowired
		private WebApplicationContext wac;
		
		@Autowired
		DataSource dataSource;
		
		private MockMvc mockMvc;

		@Before
		public void setup() throws Exception {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		}

		@Test
		public void WhenContextStartingContextNotNull() {
			ServletContext servletContext = wac.getServletContext();

			Assert.assertNotNull(servletContext);
			Assert.assertNotNull(wac.getBean("AuthorController"));

		}
		
		
		
		@Test
		public void givenGetAuthorURI_thenVerifyJsonResponse() throws Exception {
			 MvcResult mvcResult = this.mockMvc.perform(get("/authors/get/1"))
				      .andDo(print()).andExpect(status().isOk())
				      .andExpect(jsonPath("$.firstName").value("Graham"))
				      .andExpect(jsonPath("$.secondName").value("Grahamov"))
				      .andExpect(jsonPath("$.shortBiography").value("Good man"))
				      .andExpect(jsonPath("$.birhDay").value("1994-03-24"))
				      .andReturn();
				     
				    Assert.assertEquals("application/json;charset=UTF-8", 
				      mvcResult.getResponse().getContentType());
		}



}
