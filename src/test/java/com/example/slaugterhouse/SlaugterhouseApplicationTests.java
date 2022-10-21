package com.example.slaugterhouse;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.slaugterhouse.controller.AnimalController;

import com.example.slaugterhouse.model.Animal;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SlaugterhouseApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private AnimalController animalController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(animalController).isNotNull();
	}

	@Test
	void animalControllerAllMethodTest() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/animals", String.class)).contains("PIG");
	}

	@Test
	void postReturnsAnimalObjectWithId() {
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/animals","", String.class));
	}

}
