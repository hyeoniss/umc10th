package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 도전 가능 미션 목록 조회
    @Override
    public MissionResDTO.MissionListRes getAvailableMissions(String status) {
        List<Mission> missions = missionRepository.findAll();

        List<MissionResDTO.MissionListRes.MissionInfo> missionInfos = missions.stream()
                .map(m -> MissionResDTO.MissionListRes.MissionInfo.builder()
                        .missionId(m.getId())
                        .title(m.getMissionContent())
                        .reward(Math.toIntExact(m.getPoint()))
                        .status(status)
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MissionListRes.builder()
                .missions(missionInfos)
                .build();
    }

    // 미션 상세 조회
    @Override
    public MissionResDTO.MissionDetailRes getMissionDetail(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        return MissionResDTO.MissionDetailRes.builder()
                .missionId(mission.getId())
                .title(mission.getMissionContent())
                .description(mission.getMissionContent())
                .reward(Math.toIntExact(mission.getPoint()))
                .status("AVAILABLE")
                .build();
    }

    // 미션 수락
    @Override
    @Transactional
    public MissionResDTO.ChallengeRes challengeMission(String token, Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        // token으로 member 조회는 Security 구현 후 추가 예정
        return MissionResDTO.ChallengeRes.builder()
                .userMissionId(null)
                .missionId(mission.getId())
                .status("CHALLENGING")
                .build();
    }

    // 미션 완료 처리
    @Override
    @Transactional
    public MissionResDTO.CompleteRes completeMission(String token, Long userMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        return MissionResDTO.CompleteRes.builder()
                .userMissionId(memberMission.getId())
                .status("COMPLETE")
                .rewardPoint(Math.toIntExact(memberMission.getMission().getPoint()))
                .build();
    }
}