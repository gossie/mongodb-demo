package de.neuefische.mongodbdemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Joke {

    @Id
    private String id;
    private String joke;
    private String rating;

}
