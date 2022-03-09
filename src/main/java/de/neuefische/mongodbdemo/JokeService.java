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

    public List<Joke> findByRating(String rating) {
        return jokeRepository.findByRating(rating);
    }
}
