package de.neuefische.mongodbdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
@RequiredArgsConstructor
public class JokeContoller {

    private final JokeService jokeService;

    @PostMapping
    public Joke createNewJoke(@RequestBody Joke joke) {
        return jokeService.createNewJoke(joke);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> findJokeById(@PathVariable String id) {
        return ResponseEntity.of(jokeService.findById(id));
    }

    @GetMapping("/search")
    public List<Joke> findJokesByRating(@RequestParam String rating) {
        return jokeService.findByRating(rating);
    }

    @GetMapping
    public List<Joke> findAllJokes() {
        return jokeService.findAllJokes();
    }

    @PutMapping("/{id}")
    public Joke updateJoke(@PathVariable String id, @RequestBody Joke joke) {
        joke.setId(id);
        return jokeService.updateJoke(joke);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Joke> patchJoke(@PathVariable String id, @RequestBody Joke joke) {
        return ResponseEntity.of(jokeService.patchJoke(id, joke));
    }
}
