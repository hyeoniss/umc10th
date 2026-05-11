package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {

    // 도전 가능 미션 목록 조회
    MissionResDTO.MissionListRes getAvailableMissions(String status);

    // 미션 상세 조회
    MissionResDTO.MissionDetailRes getMissionDetail(Long missionId);

    // 미션 수락
    MissionResDTO.ChallengeRes challengeMission(String token, Long missionId);

    // 미션 성공 처리
    MissionResDTO.CompleteRes completeMission(String token, Long userMissionId);

    // 내가 진행중인 미션 조회 (페이지네이션)
    MissionResDTO.MyMissionPageRes getMyChallengingMissions(MissionReqDTO.MyMissionsReq request);
}