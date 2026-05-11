package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MyMissionPageRes toMyMissionPageRes(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionPageRes.MyMissionInfo> missions = page.getContent().stream()
                .map(MissionConverter::toMyMissionInfo)
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionPageRes.builder()
                .missions(missions)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    private static MissionResDTO.MyMissionPageRes.MyMissionInfo toMyMissionInfo(MemberMission memberMission) {
        return MissionResDTO.MyMissionPageRes.MyMissionInfo.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .missionContent(memberMission.getMission().getMissionContent())
                .reward(Math.toIntExact(memberMission.getMission().getPoint()))
                .status(memberMission.getStatus())
                .storeName(memberMission.getMission().getStore() != null
                        ? memberMission.getMission().getStore().getName()
                        : null)
                .build();
    }
}
