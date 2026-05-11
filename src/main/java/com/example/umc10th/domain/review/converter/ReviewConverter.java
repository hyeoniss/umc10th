package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewCursorRes toMyReviewCursorRes(
            List<Review> reviews, Long nextCursor, Boolean hasNext) {

        List<ReviewResDTO.MyReviewCursorRes.MyReviewInfo> reviewInfos = reviews.stream()
                .map(ReviewConverter::toMyReviewInfo)
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewCursorRes.builder()
                .reviews(reviewInfos)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    private static ReviewResDTO.MyReviewCursorRes.MyReviewInfo toMyReviewInfo(Review review) {
        return ReviewResDTO.MyReviewCursorRes.MyReviewInfo.builder()
                .reviewId(review.getId())
                .reviewContent(review.getReviewContent())
                .starRate(review.getStarRate())
                .reviewDate(review.getReviewDate())
                .storeName(review.getStore() != null ? review.getStore().getName() : null)
                .build();
    }
}
