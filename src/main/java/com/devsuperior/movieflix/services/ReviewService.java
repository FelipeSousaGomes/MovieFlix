package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AuthService authService;


    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setText(reviewDTO.getText());
        review.setMovie(new Movie(reviewDTO.getMovieId(), null,null,null,null,null ,null));
        review.setUser(authService.authenticated());
        review = reviewRepository.save(review);
        return new ReviewDTO(review);
    }

}
