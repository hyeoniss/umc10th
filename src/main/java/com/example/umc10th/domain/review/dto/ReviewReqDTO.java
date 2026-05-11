package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewReq(
            String content,
            Float rating
    ) {}

    // 내가 작성한 리뷰 조회 (커서 기반 페이지네이션)
    @Getter
    public static class MyReviewsReq {
        private Long memberId;
        private Long cursor;
        private Integer size;
        private String sortBy;  // "id" 또는 "starRate"
    }
}