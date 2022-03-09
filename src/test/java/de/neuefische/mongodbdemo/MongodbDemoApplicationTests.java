package de.neuefische.mongodbdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
	}

}
