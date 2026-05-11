package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰 작성
    @Override
    @Transactional
    public ReviewResDTO.CreateReviewRes createReview(String token, Long storeId, ReviewReqDTO.CreateReviewReq request) {

        // token으로 member 조회는 Security 구현 후 추가 예정
        // 임시로 Review 객체 생성 없이 응답만 반환
        return ReviewResDTO.CreateReviewRes.builder()
                .reviewId(null)
                .storeId(storeId)
                .content(request.content())
                .rating(request.rating())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 지역 내 가게 목록 조회
    @Override
    public ReviewResDTO.StoreListRes getStoresByRegion(Long regionId) {
        List<Store> stores = reviewRepository.findStoresByLocationId(regionId);

        List<ReviewResDTO.StoreListRes.StoreInfo> storeInfos = stores.stream()
                .map(s -> ReviewResDTO.StoreListRes.StoreInfo.builder()
                        .storeId(s.getId())
                        .name(s.getName())
                        .category("카테고리") // Store에 category 필드 추가 필요
                        .rating(s.getScore())
                        .build())
                .collect(Collectors.toList());

        return ReviewResDTO.StoreListRes.builder()
                .stores(storeInfos)
                .build();
    }

    // 가게 상세 조회
    @Override
    public ReviewResDTO.StoreDetailRes getStoreDetail(Long storeId) {
        Store store = reviewRepository.findStoreById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        return ReviewResDTO.StoreDetailRes.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category("카테고리") // Store에 category 필드 추가 필요
                .address(store.getAddress())
                .rating(store.getScore())
                .menus(List.of()) // Store에 menus 필드 추가 필요
                .build();
    }

    // 내가 작성한 리뷰 조회 (커서 기반 페이지네이션)
    @Override
    public ReviewResDTO.MyReviewCursorRes getMyReviews(ReviewReqDTO.MyReviewsReq request) {
        int size = request.getSize() != null ? request.getSize() : 10;
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Review> reviews;
        String sortBy = request.getSortBy() != null ? request.getSortBy() : "id";

        if ("starRate".equals(sortBy)) {
            reviews = getReviewsByStarRate(request, pageable);
        } else {
            reviews = getReviewsById(request, pageable);
        }

        boolean hasNext = reviews.size() > size;
        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        Long nextCursor = hasNext && !reviews.isEmpty()
                ? reviews.get(reviews.size() - 1).getId()
                : null;

        return ReviewConverter.toMyReviewCursorRes(reviews, nextCursor, hasNext);
    }

    private List<Review> getReviewsById(ReviewReqDTO.MyReviewsReq request, Pageable pageable) {
        if (request.getCursor() == null) {
            return reviewRepository.findByMemberIdOrderByIdDesc(request.getMemberId(), pageable);
        }
        return reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(
                request.getMemberId(), request.getCursor(), pageable);
    }

    private List<Review> getReviewsByStarRate(ReviewReqDTO.MyReviewsReq request, Pageable pageable) {
        if (request.getCursor() == null) {
            return reviewRepository.findByMemberIdOrderByStarRateDescIdDesc(request.getMemberId(), pageable);
        }
        Review cursorReview = reviewRepository.findById(request.getCursor())
                .orElseThrow(() -> new RuntimeException("커서 리뷰를 찾을 수 없습니다."));
        return reviewRepository.findByMemberIdAndStarRateLessThanOrderByStarRateDescIdDesc(
                request.getMemberId(), cursorReview.getStarRate(), cursorReview.getId(), pageable);
    }
}