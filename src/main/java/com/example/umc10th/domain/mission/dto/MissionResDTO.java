package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    // 미션 목록
    @Builder
    public record MissionListRes(
            List<MissionInfo> missions
    ) {
        @Builder
        public record MissionInfo(
                Long missionId,
                String title,
                Integer reward,
                String status
        ) {}
    }

    // 미션 상세
    @Builder
    public record MissionDetailRes(
            Long missionId,
            String title,
            String description,
            Integer reward,
            String status
    ) {}

    // 미션 수락
    @Builder
    public record ChallengeRes(
            Long userMissionId,
            Long missionId,
            String status
    ) {}

    // 미션 성공 처리
    @Builder
    public record CompleteRes(
            Long userMissionId,
            String status,
            Integer rewardPoint
    ) {}

    // 내가 진행중인 미션 목록 (페이지네이션)
    @Builder
    public record MyMissionPageRes(
            List<MyMissionInfo> missions,
            Integer currentPage,
            Integer totalPages,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
        @Builder
        public record MyMissionInfo(
                Long memberMissionId,
                Long missionId,
                String missionContent,
                Integer reward,
                String status,
                String storeName
        ) {}
    }
}