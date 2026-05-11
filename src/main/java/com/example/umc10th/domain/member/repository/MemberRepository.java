package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 미션 목록 조회 (페이징 - @Query)
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    List<com.example.umc10th.domain.mission.entity.mapping.MemberMission> findMissionsByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") String status);

    // 리뷰 목록 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId")
    List<com.example.umc10th.domain.review.entity.Review> findReviewsByMemberId(
            @Param("memberId") Long memberId);
}
