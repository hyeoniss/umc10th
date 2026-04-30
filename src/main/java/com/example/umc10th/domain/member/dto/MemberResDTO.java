package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    // 마이페이지
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    // 회원가입
    @Builder
    public record SignupRes(
            Long userId,
            String nickname,
            String email
    ) {}

    // 로그인
    @Builder
    public record LoginRes(
            Long userId,
            String accessToken
    ) {}

    // 사용자 기본정보 조회 / 수정
    @Builder
    public record MemberInfoRes(
            Long userId,
            String nickname,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    // 미션 목록
    @Builder
    public record MissionListRes(
            List<MissionInfo> missions
    ) {
        @Builder
        public record MissionInfo(
                Long missionId,
                String title,
                String status,
                Integer reward
        ) {}
    }

    // 리뷰 목록
    @Builder
    public record ReviewListRes(
            List<ReviewInfo> reviews
    ) {
        @Builder
        public record ReviewInfo(
                Long reviewId,
                String content,
                Float rating,
                LocalDateTime createdAt
        ) {}
    }
}