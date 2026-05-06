package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.mission.entity.Store;
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
}