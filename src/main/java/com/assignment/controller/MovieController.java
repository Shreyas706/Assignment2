package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Movie;
import com.assignment.service.MovieService;
import com.assignment.utill.MovieResponse;
import com.assignment.validate.MovieValidator;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieValidator movieValidator;

	@GetMapping("/movie")
	public List<Movie> getAllMovie() {
		return movieService.getAllMovies();
	}

	@PostMapping("/movie")
	public MovieResponse addMovie(@RequestBody Movie movie, BindingResult errors) {

		MovieResponse movieResponse = new MovieResponse();

		if (movieValidator.supports(Movie.class)) {
			movieValidator.validate(movie, errors);
		}

		if (errors.hasErrors()) {
			movieResponse.setStatus(HttpStatus.METHOD_FAILURE);
			movieResponse.setMessage(errors.getAllErrors().toString());
		}

		try {
			movieService.addMovie(movie);
			movieResponse.setStatus(HttpStatus.OK);
			movieResponse.setMessage("Movie inserted successfully");

		} catch (RuntimeException e) {
			movieResponse.setStatus(HttpStatus.METHOD_FAILURE);
			movieResponse.setMessage("Movie insertion failed. "+e.getMessage());
		}
		return movieResponse;
	}

	@GetMapping("/movie/{id}")
	public Movie getMovieByid(@PathVariable("id") long id) {
		return movieService.getMovie(id);
	}

	@DeleteMapping("/movie/{id}")
	public MovieResponse deleteMovieByid(@PathVariable("id") long id) {
		MovieResponse movieResponse = new MovieResponse();
		try {
			movieService.deleteMovie(id);
			movieResponse.setStatus(HttpStatus.OK);
			movieResponse.setMessage("movie deleted successfully");
		} catch (RuntimeException e) {
			movieResponse.setStatus(HttpStatus.METHOD_FAILURE);
			movieResponse.setMessage("movie deleted successfully");
		}
		return movieResponse;
	}
	
	
	@PostMapping("/movie/{id}")
	public MovieResponse updateMovie(@PathVariable("id") long id,@RequestBody Movie movie, BindingResult errors) {

		MovieResponse movieResponse = new MovieResponse();

		if (movieValidator.supports(Movie.class)) {
			movieValidator.validate(movie, errors);
		}

		if (errors.hasErrors()) {
			movieResponse.setStatus(HttpStatus.METHOD_FAILURE);
			movieResponse.setMessage(errors.getAllErrors().toString());
		}

		try {
			movieService.updateMovie(id,movie);
			movieResponse.setStatus(HttpStatus.OK);
			movieResponse.setMessage("Movie updated successfully");

		} catch (RuntimeException e) {
			movieResponse.setStatus(HttpStatus.METHOD_FAILURE);
			movieResponse.setMessage("Movie update failed. "+e.getMessage());
		}
		return movieResponse;
	}
	
}
