package com.assignment.service;

import java.util.List;

import com.assignment.entity.Movie;

public interface MovieService {
	
	public List<Movie> getAllMovies();
	
	public void addMovie(Movie movie);
	
	public Movie getMovie(long id);
	
	public void deleteMovie(long id);
	
	public void updateMovie(long id,Movie movie);

}
