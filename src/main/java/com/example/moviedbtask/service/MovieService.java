package com.example.moviedbtask.service;

import com.example.moviedbtask.model.Movie;
import com.example.moviedbtask.repo.MovieRepo;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class MovieService {
    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> getMovieList(){
        return movieRepo.getMovieList();
    }

    public List<Movie> addMovieToList(Movie movie){
        return movieRepo.addMovieToList(movie);
    }

    public List<Movie> deleteMovieFromList(String id){
        return movieRepo.deleteMovieFromList(id);
    }

    public Movie getMovieById(String id) {
        return movieRepo.getMovieById(id);
    }

    public Movie updateMovieById(String id, boolean favorit){
        return movieRepo.updateMovieById(id, favorit);
    }

}
