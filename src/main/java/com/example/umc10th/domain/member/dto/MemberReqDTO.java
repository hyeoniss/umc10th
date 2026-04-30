package com.example.umc10th.domain.member.dto;

import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            Long id
    ) {}

    // 회원가입
    public record SignupReq(
            String email,
            String password,
            String nickname,
            String phoneNumber
    ) {}

    // 로그인
    public record LoginReq(
            String email,
            String password
    ) {}

    // 사용자 정보 수정
    public record UpdateReq(
            String nickname,
            String phoneNumber
    ) {}

    // 선호 음식 선택
    public record FoodPreferenceReq(
            List<String> foodCategories
    ) {}
}