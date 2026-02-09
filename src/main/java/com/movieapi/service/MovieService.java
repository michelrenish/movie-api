package com.movieapi.service;

import com.movieapi.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service layer for managing movies.
 * Uses an in-memory ArrayList to store movies.
 */
@Service
public class MovieService {

    // In-memory storage for movies
    private final List<Movie> movies = new ArrayList<>();
    
    // Auto-incrementing ID generator
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Constructor that initializes the service with some sample movies.
     */
    public MovieService() {
        // Pre-populate with sample movies for testing
        movies.add(new Movie(
            idGenerator.getAndIncrement(),
            "The Shawshank Redemption",
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            1994,
            "Drama",
            9.3
        ));
        
        movies.add(new Movie(
            idGenerator.getAndIncrement(),
            "Inception",
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea.",
            2010,
            "Sci-Fi",
            8.8
        ));
        
        movies.add(new Movie(
            idGenerator.getAndIncrement(),
            "The Dark Knight",
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological tests.",
            2008,
            "Action",
            9.0
        ));
    }

    /**
     * Adds a new movie to the collection.
     * Automatically generates and assigns an ID.
     *
     * @param movie The movie to add (without ID)
     * @return The added movie with generated ID
     */
    public Movie addMovie(Movie movie) {
        movie.setId(idGenerator.getAndIncrement());
        movies.add(movie);
        return movie;
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id The ID of the movie to retrieve
     * @return Optional containing the movie if found, empty otherwise
     */
    public Optional<Movie> getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    /**
     * Retrieves all movies in the collection.
     *
     * @return List of all movies
     */
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }
}
