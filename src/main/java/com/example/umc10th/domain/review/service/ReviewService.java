package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public interface ReviewService {

    // 리뷰 작성
    ReviewResDTO.CreateReviewRes createReview(String token, Long storeId, ReviewReqDTO.CreateReviewReq request);

    // 지역 내 가게 목록 조회
    ReviewResDTO.StoreListRes getStoresByRegion(Long regionId);

    // 가게 상세 조회
    ReviewResDTO.StoreDetailRes getStoreDetail(Long storeId);
}