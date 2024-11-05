package com.coming.pet_store_coming_be.controller.review;

import com.coming.pet_store_coming_be.dto.review.ReviewDTO;
import com.coming.pet_store_coming_be.service.review.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO review) {
        reviewService.addReview(review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }

    // 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

    // 특정 제품의 리뷰 조회
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(@PathVariable String productId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}