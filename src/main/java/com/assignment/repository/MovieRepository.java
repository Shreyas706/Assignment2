package com.assignment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Modifying
	@Transactional
	@Query(value="delete from Movie m where m.id = ?1")
	void deleteByMovieId(long id);
}
