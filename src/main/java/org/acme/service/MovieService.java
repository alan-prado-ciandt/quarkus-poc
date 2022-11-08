package org.acme.service;

import io.quarkus.hibernate.orm.PersistenceUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.acme.model.movie.Movie;
import org.acme.response.PageResponse;

@ApplicationScoped
public class MovieService {

    private static final String MOVIE_NAME = Movie.class.getName();
    private final EntityManager em;

    public MovieService(@PersistenceUnit("movies") final EntityManager em) {
        this.em = em;
    }

    @Transactional
    public PageResponse<Movie> list(int pageSize, int page) {
        return new PageResponse<>(em.createQuery("FROM " + MOVIE_NAME)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * page)
                .getResultList());
    }

    @Transactional
    public Movie save(Movie movie) {
        em.persist(movie);
        return movie;
    }
}
