package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 마이페이지
    @Override
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // 회원가입 (Security 구현 전 임시)
    @Override
    @Transactional
    public MemberResDTO.SignupRes signup(MemberReqDTO.SignupReq request) {
        return MemberResDTO.SignupRes.builder()
                .userId(null)
                .nickname(request.nickname())
                .email(request.email())
                .build();
    }

    // 로그인 (Security 구현 전 임시)
    @Override
    public MemberResDTO.LoginRes login(MemberReqDTO.LoginReq request) {
        return MemberResDTO.LoginRes.builder()
                .userId(null)
                .accessToken("임시토큰")
                .build();
    }

    // 사용자 정보 수정 (Security 구현 전 임시)
    @Override
    @Transactional
    public MemberResDTO.MemberInfoRes updateMemberInfo(MemberReqDTO.UpdateReq request) {
        return MemberResDTO.MemberInfoRes.builder()
                .userId(null)
                .nickname(request.nickname())
                .email(null)
                .phoneNumber(request.phoneNumber())
                .point(null)
                .build();
    }

    // 진행중/완료 미션 목록 조회
    @Override
    public MemberResDTO.MissionListRes getMissions(Long memberId, String status) {
        List<MemberMission> memberMissions = memberRepository
                .findMissionsByMemberIdAndStatus(memberId, status);

        List<MemberResDTO.MissionListRes.MissionInfo> missionInfos = memberMissions.stream()
                .map(mm -> MemberResDTO.MissionListRes.MissionInfo.builder()
                        .missionId(mm.getMission().getId())
                        .title(mm.getMission().getMissionContent())
                        .status(mm.getStatus())
                        .reward(Math.toIntExact(mm.getMission().getPoint()))
                        .build())
                .collect(Collectors.toList());

        return MemberResDTO.MissionListRes.builder()
                .missions(missionInfos)
                .build();
    }

    // 본인 리뷰 조회
    @Override
    public MemberResDTO.ReviewListRes getReviews(Long userId) {
        List<Review> reviews = memberRepository.findReviewsByMemberId(userId);

        List<MemberResDTO.ReviewListRes.ReviewInfo> reviewInfos = reviews.stream()
                .map(r -> MemberResDTO.ReviewListRes.ReviewInfo.builder()
                        .reviewId(r.getId())
                        .content(r.getReviewContent())
                        .rating(Float.valueOf(r.getStarRate()))
                        .createdAt(r.getReviewDate())
                        .build())
                .collect(Collectors.toList());

        return MemberResDTO.ReviewListRes.builder()
                .reviews(reviewInfos)
                .build();
    }
}