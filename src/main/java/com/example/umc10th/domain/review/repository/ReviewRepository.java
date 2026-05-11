package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 지역 내 가게 목록 조회
    @Query("SELECT s FROM Store s WHERE s.location.id = :locationId")
    List<Store> findStoresByLocationId(@Param("locationId") Long locationId);

    // 가게 상세 조회
    @Query("SELECT s FROM Store s WHERE s.id = :storeId")
    Optional<Store> findStoreById(@Param("storeId") Long storeId);

    // 커서 기반 페이지네이션 - ID 순 (첫 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    List<Review> findByMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 커서 기반 페이지네이션 - ID 순 (커서 이후)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable);

    // 커서 기반 페이지네이션 - 별점 순 (첫 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.starRate DESC, r.id DESC")
    List<Review> findByMemberIdOrderByStarRateDescIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 커서 기반 페이지네이션 - 별점 순 (커서 이후)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.starRate < :cursorStarRate OR (r.starRate = :cursorStarRate AND r.id < :cursorId)) " +
            "ORDER BY r.starRate DESC, r.id DESC")
    List<Review> findByMemberIdAndStarRateLessThanOrderByStarRateDescIdDesc(
            @Param("memberId") Long memberId,
            @Param("cursorStarRate") Long cursorStarRate,
            @Param("cursorId") Long cursorId,
            Pageable pageable);
}