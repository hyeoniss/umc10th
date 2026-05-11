package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class ChallengeReq {
    }

    @Getter
    public static class MyMissionsReq {
        private Long memberId;
        private Integer page;
        private Integer size;
    }
}