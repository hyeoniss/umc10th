package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record CreateReviewRes(
            Long reviewId,
            Long storeId,
            String content,
            Float rating,
            LocalDateTime createdAt
    ) {}

    // 가게 목록
    @Builder
    public record StoreListRes(
            List<StoreInfo> stores
    ) {
        @Builder
        public record StoreInfo(
                Long storeId,
                String name,
                String category,
                Float rating
        ) {}
    }

    // 가게 상세
    @Builder
    public record StoreDetailRes(
            Long storeId,
            String name,
            String category,
            String address,
            Float rating,
            List<String> menus
    ) {}

    // 내가 작성한 리뷰 목록 (커서 기반 페이지네이션)
    @Builder
    public record MyReviewCursorRes(
            List<MyReviewInfo> reviews,
            Long nextCursor,
            Boolean hasNext
    ) {
        @Builder
        public record MyReviewInfo(
                Long reviewId,
                String reviewContent,
                Long starRate,
                LocalDateTime reviewDate,
                String storeName
        ) {}
    }
}