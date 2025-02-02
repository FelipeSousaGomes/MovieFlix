package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        Movie movie = result.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return new MovieDetailsDTO(movie);
    }

    @Transactional(readOnly = true)
    public ReviewDTO findByReview(Long id) {
        Optional<Review>  result = reviewRepository.findById(id);
        Review review = result.orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return new ReviewDTO(review) ;
    }


    public Page<MovieCardDTO> findAllPaged(String title, String genreId, Pageable pageable) {
        Page<Movie> result = movieRepository.findMoviesByGenreAndTitle(Long.valueOf(genreId), title, pageable);

        Page<MovieCardDTO> list = result.map(movie -> new MovieCardDTO(movie));

        return list;
    }
}
