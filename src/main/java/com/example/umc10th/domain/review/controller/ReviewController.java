package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;  // 이거 추가!

    // 리뷰 작성
    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @RequestHeader("Authorization") String token,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewReq request) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_OK;
        return ApiResponse.onSuccess(code, reviewService.createReview(token, storeId, request));
    }

    // 지역 내 가게 조회
    @GetMapping("/v1/regions/{regionId}/stores")
    public ApiResponse<ReviewResDTO.StoreListRes> getStoresByRegion(
            @PathVariable Long regionId) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getStoresByRegion(regionId));
    }

    // 가게 상세 조회
    @GetMapping("/v1/stores/{storeId}")
    public ApiResponse<ReviewResDTO.StoreDetailRes> getStoreDetail(
            @PathVariable Long storeId) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getStoreDetail(storeId));
    }
}