package org.acme.resource;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.model.movie.Movie;
import org.acme.request.CreateMovie;
import org.acme.request.SearchMovies;
import org.acme.response.PageResponse;
import org.acme.service.MovieService;

@Path("/movies")
public class MovieResource {

    private final MovieService movieService;

    public MovieResource(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PageResponse<Movie> getMovies(final @BeanParam @Valid SearchMovies searchMovies) {
        return movieService.list(searchMovies.getPageSize(), searchMovies.getPage());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Movie postMovies(final CreateMovie movie) {
        return movieService.save(new Movie(movie.name(), movie.description()));
    }
}