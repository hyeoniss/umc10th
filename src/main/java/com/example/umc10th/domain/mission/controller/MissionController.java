package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/missions")
    public ApiResponse<MissionResDTO.MissionListRes> getAvailableMissions(
            @RequestParam String status) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getAvailableMissions(status));
    }

    @GetMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetailRes> getMissionDetail(
            @PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissionDetail(missionId));
    }

    @PostMapping("/v1/missions/{missionId}/challenge")
    public ApiResponse<MissionResDTO.ChallengeRes> challengeMission(
            @RequestHeader("Authorization") String token,
            @PathVariable Long missionId) {
        BaseSuccessCode code = MissionSuccessCode.CHALLENGE_OK;
        return ApiResponse.onSuccess(code, missionService.challengeMission(token, missionId));
    }

    @PostMapping("/v1/users/me/missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.CompleteRes> completeMission(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userMissionId) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(token, userMissionId));
    }

    @PostMapping("/v1/users/me/missions/challenging")
    public ApiResponse<MissionResDTO.MyMissionPageRes> getMyChallengingMissions(
            @RequestBody MissionReqDTO.MyMissionsReq request) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyChallengingMissions(request));
    }
}