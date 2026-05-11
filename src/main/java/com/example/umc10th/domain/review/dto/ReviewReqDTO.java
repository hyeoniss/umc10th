package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewReq(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content,

            @NotNull(message = "평점은 필수입니다.")
            @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
            @Max(value = 5, message = "평점은 5 이하여야 합니다.")
            Float rating
    ) {}

    // 내가 작성한 리뷰 조회 (커서 기반 페이지네이션)
    @Getter
    public static class MyReviewsReq {
        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        private Long cursor;

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer size;

        private String sortBy;  // "id" 또는 "starRate"
    }
}