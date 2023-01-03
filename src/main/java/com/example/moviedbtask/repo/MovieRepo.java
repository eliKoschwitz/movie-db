package com.example.moviedbtask.repo;

import com.example.moviedbtask.model.Movie;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.List;


@Data
@Repository
public class MovieRepo {
    private final List<Movie> movieList;// = new ArrayList<>(List.of( new Movie("1","Harry Potter", "https://m.media-amazon.com/images/I/5165He67NEL.jpg","2001")));

    public MovieRepo(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList(){
        return movieList;
    }

    public List<Movie> addMovieToList(Movie movie){
        newId(movie);
        if(movie.getUrl().equals("")){
            movie.setUrl("https://i.otto.de/i/otto/cd4c0898-1c76-5ca9-b2f0-c596d6a36b87?w=1550&h=2322");
        }
        movieList.add(movie);
        return movieList;
    }

    private void newId(Movie movie){
        if(movieList.size() == 0) {
            movie.setId("0");
        } else {
            String size = String.valueOf(movieList.size());
            movie.setId(size);
        }
    }

    public List<Movie> deleteMovieFromList(String id){
        Movie safeMovie = null;
        for(Movie currentMovie : movieList){
            if(currentMovie.getId().equals(id)){
                safeMovie = currentMovie;
            }
        }
        movieList.remove(safeMovie);
        return movieList;
    }

    public Movie getMovieById(String id){
        for (Movie currentMovie : movieList){
           if(currentMovie.getId().equals(id)) {
               return currentMovie;
           }
        }
        return null;
        //throw new Exception(NoSuchElementException);
    }

    public Movie updateMovieById(String id, boolean favorit){
        for (Movie currentMovie : movieList){
            if(currentMovie.getId().equals(id)) {
                currentMovie.setFavorit(favorit);
                return currentMovie;
            }
        }
        return null;
    }

}