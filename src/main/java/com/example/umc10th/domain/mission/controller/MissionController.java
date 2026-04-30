package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 도전 가능 미션 목록 조회
    @GetMapping("/v1/missions")
    public ApiResponse<MissionResDTO.MissionListRes> getAvailableMissions(
            @RequestParam String status) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 미션 상세 조회
    @GetMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetailRes> getMissionDetail(
            @PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 미션 수락
    @PostMapping("/v1/missions/{missionId}/challenge")
    public ApiResponse<MissionResDTO.ChallengeRes> challengeMission(
            @RequestHeader("Authorization") String token,
            @PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.CHALLENGE_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 미션 성공 처리
    @PostMapping("/v1/users/me/missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.CompleteRes> completeMission(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userMissionId) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_OK;
        return ApiResponse.onSuccess(code, null);
    }
}