package org.acme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Movie {

    @Id
    @SequenceGenerator(name = "movieSeq", sequenceName = "movie_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "movieSeq")
    private long id;
    private String name;
    private String description;

    public Movie() {}

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
}
