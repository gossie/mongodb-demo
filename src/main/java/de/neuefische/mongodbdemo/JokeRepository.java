package de.neuefische.mongodbdemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends MongoRepository<Joke, String> {

    List<Joke> findByRating(String rating);

}
