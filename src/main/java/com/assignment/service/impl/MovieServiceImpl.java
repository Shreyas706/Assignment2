package com.assignment.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entity.Movie;
import com.assignment.repository.MovieRepository;
import com.assignment.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public void addMovie(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public Movie getMovie(long id) {
		Movie movie=null;
		try {
			movie=movieRepository.findById(id).get();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Transactional
	@Override
	public void deleteMovie(long id) {
		//Movie movie=movieRepository.findById(id).get();
		movieRepository.deleteByMovieId(id);
	}

	@Transactional
	@Override
	public void updateMovie(long id,Movie movie) {
		Movie movie2=movieRepository.findById(id).get();
		movie2.setCategory(movie.getCategory());
		movie2.setTitle(movie.getTitle());
		movie2.setRating(movie.getRating());
	}

	
}
