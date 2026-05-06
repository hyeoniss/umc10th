package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;

public interface MemberService {

    // 마이페이지
    MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto);

    // 회원가입
    MemberResDTO.SignupRes signup(MemberReqDTO.SignupReq request);

    // 로그인
    MemberResDTO.LoginRes login(MemberReqDTO.LoginReq request);

    // 사용자 정보 수정
    MemberResDTO.MemberInfoRes updateMemberInfo(MemberReqDTO.UpdateReq request);

    // 진행중/완료 미션 목록 조회
    MemberResDTO.MissionListRes getMissions(Long memberId, String status);

    // 본인 리뷰 조회
    MemberResDTO.ReviewListRes getReviews(Long userId);
}