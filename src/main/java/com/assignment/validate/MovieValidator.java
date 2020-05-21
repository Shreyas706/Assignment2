package com.assignment.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.assignment.entity.Movie;

@Component
public class MovieValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().isAssignableFrom(Movie.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Movie movie=(Movie) target;
		
		if(movie.getRating()<0.5f || movie.getRating()>5.0f)
			errors.reject("error.rating", "rating should be between 0.5 to 5");
		
	}

	
}
