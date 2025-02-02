package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/movies")
public class MovieController {


    @Autowired
    private MovieService service;


    @GetMapping("/{id}" )
    public ResponseEntity<MovieDetailsDTO> findByMovieId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/{id}/reviews" )
    public ResponseEntity<ReviewDTO> findByReviewId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findByReview(id));
    }
}
