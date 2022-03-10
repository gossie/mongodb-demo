package de.neuefische.mongodbdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MongodbDemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		Joke joke = new Joke();
		joke.setJoke("Was ist grün, hängt an der Türklinke und guckt durchs Schlüsselloch? Ein Spionat.");
		joke.setRating("superschlecht");

		ResponseEntity<Joke> postResponse = restTemplate.postForEntity("/api/jokes", joke, Joke.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody().getId()).isNotNull();
		assertThat(postResponse.getBody().getJoke()).isEqualTo("Was ist grün, hängt an der Türklinke und guckt durchs Schlüsselloch? Ein Spionat.");
		assertThat(postResponse.getBody().getRating()).isEqualTo("superschlecht");

		ResponseEntity<Joke> getResponse = restTemplate.getForEntity("/api/jokes/" + postResponse.getBody().getId(), Joke.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponse.getBody().getId()).isNotNull();
		assertThat(getResponse.getBody().getJoke()).isEqualTo("Was ist grün, hängt an der Türklinke und guckt durchs Schlüsselloch? Ein Spionat.");
		assertThat(getResponse.getBody().getRating()).isEqualTo("superschlecht");

		/*
		Joke deltaData = new Joke();
		deltaData.setRating("mittel");
		//Joke patchedJoke = restTemplate.patchForObject("/api/jokes/" + getResponse.getBody().getId(), deltaData, Joke.class);
		ResponseEntity<Joke> patchResponse = restTemplate.exchange("/api/jokes/" + getResponse.getBody().getId(), HttpMethod.PUT, new HttpEntity<>(deltaData), Joke.class);
		assertThat(patchResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		//assertThat(patchedJoke).isNotNull();
		*/
		ResponseEntity<Joke> deletedJokeResponse = restTemplate.exchange("/api/jokes/" + getResponse.getBody().getId(), HttpMethod.DELETE, null, Joke.class);
		assertThat(deletedJokeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(deletedJokeResponse.getBody().getId()).isEqualTo(getResponse.getBody().getId());
		assertThat(deletedJokeResponse.getBody().getJoke()).isEqualTo("Was ist grün, hängt an der Türklinke und guckt durchs Schlüsselloch? Ein Spionat.");
		assertThat(deletedJokeResponse.getBody().getRating()).isEqualTo("superschlecht");

		ResponseEntity<Joke> secondGetResponse = restTemplate.getForEntity("/api/jokes/" + deletedJokeResponse.getBody().getId(), Joke.class);
		assertThat(secondGetResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
