package com.coming.pet_store_coming_be.service.review;

import com.coming.pet_store_coming_be.dao.review.ReviewDAO;
import com.coming.pet_store_coming_be.dto.review.ReviewDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    // 리뷰 등록
    public void addReview(ReviewDTO review) {
        reviewDAO.insertReview(review);
    }

    // 리뷰 삭제
    public void deleteReview(String id) {
        reviewDAO.deleteReview(id);
    }

    // 특정 상품 리뷰 조회
    public List<ReviewDTO> getReviewsByProductId(String productId) {
        return reviewDAO.getReviewsByProductId(productId);
    }
}