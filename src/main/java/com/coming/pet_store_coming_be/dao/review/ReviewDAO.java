package com.coming.pet_store_coming_be.dao.review;

import com.coming.pet_store_coming_be.dto.review.ReviewDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewDAO {
    
    // 리뷰 추가
    void insertReview(ReviewDTO review);
    
    // 리뷰 삭제
    void deleteReview(@Param("id") String id);
    // 상품 리뷰 조회
    List<ReviewDTO> getReviewsByProductId(@Param("productId") String productId);
    
}
