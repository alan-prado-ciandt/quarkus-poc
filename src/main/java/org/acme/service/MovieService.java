package org.acme.service;

import io.quarkus.hibernate.orm.PersistenceUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.acme.model.movie.Movie;
import org.acme.model.movieh2.MovieH2;
import org.acme.response.PageResponse;

@ApplicationScoped
public class MovieService {

    private static final String MOVIE_NAME = Movie.class.getName();
    private static final String MOVIE_H2_NAME = MovieH2.class.getName();

    private final EntityManager em;
    private final EntityManager emH2;

    public MovieService(@PersistenceUnit("movies") final EntityManager em, @PersistenceUnit("movies_h2") final EntityManager emH2) {
        this.em = em;
        this.emH2 = emH2;
    }

    @Transactional
    public PageResponse<Movie> list(int pageSize, int page, String datasource) {
        return new PageResponse<>(getDatasource(datasource).createQuery("FROM " + getTableName(datasource))
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * page)
                .getResultList());
    }

    private String getTableName(String datasource) {
        return datasource.equals("movies") ? MOVIE_NAME : MOVIE_H2_NAME;
    }

    private EntityManager getDatasource(String datasource) {
        System.out.println("datasource.equals(\"movies\")="+datasource.equals("movies"));
        return datasource.equals("movies") ? em : emH2;
    }

    @Transactional
    public Movie save(Movie movie) {
        em.persist(movie);
        if(movie.getId() % 2 == 0) {
            emH2.persist(new MovieH2(movie.getName(), movie.getDescription()));
        }
        return movie;
    }
}
