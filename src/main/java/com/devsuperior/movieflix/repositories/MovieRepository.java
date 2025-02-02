package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
    SELECT m FROM Movie m 
    WHERE (:genreId = 0 OR m.genre.id = :genreId) 
    AND LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))
""")
    Page<Movie> findMoviesByGenreAndTitle(@Param("genreId") Long genreId, @Param("title") String title, Pageable pageable);


}
