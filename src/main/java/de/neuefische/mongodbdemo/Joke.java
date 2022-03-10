package de.neuefische.mongodbdemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="jokes")
@Data
public class Joke {

    @Id
    private String id;
    private String joke;
    private String rating;

    public Joke patch(Joke joke) {
        if (joke.getJoke() != null) {
            setJoke(joke.getJoke());
        }

        if (joke.getRating() != null) {
            setRating(joke.getRating());
        }
        return this;
    }

    public Joke update(Joke joke) {
        setJoke(joke.getJoke());
        setRating(joke.getRating());
        return this;
    }
}
