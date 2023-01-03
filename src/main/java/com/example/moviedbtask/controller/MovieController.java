package com.example.moviedbtask.controller;

import com.example.moviedbtask.model.Movie;
import com.example.moviedbtask.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/movies")
    public List<Movie> getMovieList(){
        return movieService.getMovieList();
    }

    @PostMapping("/api/movies")
    public List<Movie> addMovieToList(@RequestBody Movie movie){
        return movieService.addMovieToList(movie);
    }

    @DeleteMapping("/api/movies/{id}")
    public List<Movie> deleteMovieFromList(@PathVariable String id){
        return movieService.deleteMovieFromList(id);
    }

    @GetMapping("/api/movies/{id}")
    public Movie getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @PutMapping("/api/movies/{id}")
    public Movie updateMovieById(@PathVariable String id, @RequestBody Movie movie){
        System.out.println(movie.isFavorit());
        return movieService.updateMovieById(id, movie.isFavorit());
    }

}