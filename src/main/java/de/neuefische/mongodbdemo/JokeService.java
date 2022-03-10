package de.neuefische.mongodbdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JokeService {

    private final JokeRepository jokeRepository;

    public Joke createNewJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public Optional<Joke> findById(String id) {
        return jokeRepository.findById(id);
    }

    public List<Joke> findAllJokes() {
        return jokeRepository.findAll();
    }

    public List<Joke> findByRating(String rating) {
        return jokeRepository.findByRating(rating);
    }

    public Optional<Joke> updateJoke(String id, Joke joke) {
        return jokeRepository.findById(id)
                .map(j -> j.update(joke))
                .map(jokeRepository::save);
    }

    public Optional<Joke> patchJoke(String id, Joke joke) {
        /*
        Optional<Joke> optionalJoke = jokeRepository.findById(id);
        if (optionalJoke.isPresent()) {
            Joke jokeFromDatabase = optionalJoke.get();
            if (joke.getJoke() != null) {
                jokeFromDatabase.setJoke(joke.getJoke());
            }

            if (joke.getRating() != null) {
                jokeFromDatabase.setRating(joke.getRating());
            }
            return Optional.of(jokeRepository.save(jokeFromDatabase));
        } else {
            return Optional.empty();
        }
         */

        return jokeRepository.findById(id)
                .map(j -> j.patch(joke))
                .map(jokeRepository::save);
    }
}
