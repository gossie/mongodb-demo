package de.neuefische.mongodbdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class JokeServiceTest {

    @Test
    void shouldCreateNewJoke() {
        // Given
        Joke jokeToCreate = new Joke();
        jokeToCreate.setJoke("Warum gehen Ameisen nicht in die Kirche? Weil sie Insekten sind.");
        jokeToCreate.setRating("*****");

        Joke savedJoke = new Joke();
        savedJoke.setId("4711");
        savedJoke.setJoke("Warum gehen Ameisen nicht in die Kirche? Weil sie Insekten sind.");
        savedJoke.setRating("*****");

        JokeRepository jokeRepository = mock(JokeRepository.class);
        Mockito.when(jokeRepository.save(jokeToCreate)).thenReturn(savedJoke);

        JokeService jokeService = new JokeService(jokeRepository);

        // When
        Joke result = jokeService.createNewJoke(jokeToCreate);

        // Then
        Assertions.assertThat(result).isSameAs(savedJoke);
    }
}