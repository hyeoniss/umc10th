package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewReq(
            String content,
            Float rating
    ) {}
}