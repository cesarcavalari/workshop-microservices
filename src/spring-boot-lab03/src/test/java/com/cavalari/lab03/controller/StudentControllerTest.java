package com.cavalari.lab03.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cavalari.lab03.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;
	
	@Test
	public void testGetStudents() throws Exception {
		mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].ident", equalTo(1)))
				.andExpect(jsonPath("$.[0].name", equalTo("João")))
				.andExpect(jsonPath("$.[1].ident", equalTo(2)))
				.andExpect(jsonPath("$.[1].name", equalTo("maria")));
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/students/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.ident", equalTo(1)))
				.andExpect(jsonPath("$.name", equalTo("João")));
	}

	@Test
	public void testCreate() throws Exception {
		Student student = new Student("Patrick", 1565188, "patrick@gmail.com");
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonParser.writeValueAsString(student)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.ident", notNullValue()))
				.andExpect(jsonPath("$.name", equalTo("Patrick")))
				.andExpect(jsonPath("$.email", equalTo("patrick@gmail.com")));
	}

}
