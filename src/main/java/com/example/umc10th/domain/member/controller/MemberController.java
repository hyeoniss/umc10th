package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원가입
    @PostMapping("/v1/auth/signup")
    public ApiResponse<MemberResDTO.SignupRes> signup(
            @RequestBody MemberReqDTO.SignupReq request) {
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 로그인
    @PostMapping("/v1/auth/login")
    public ApiResponse<MemberResDTO.LoginRes> login(
            @RequestBody MemberReqDTO.LoginReq request) {
        BaseSuccessCode code = MemberSuccessCode.LOGIN_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 로그아웃
    @PostMapping("/v1/auth/logout")
    public ApiResponse<Void> logout(
            @RequestHeader("Authorization") String token) {
        BaseSuccessCode code = MemberSuccessCode.LOGOUT_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 회원 탈퇴
    @DeleteMapping("/v1/users/me")
    public ApiResponse<Void> deleteMember(
            @RequestHeader("Authorization") String token) {
        BaseSuccessCode code = MemberSuccessCode.DELETE_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 사용자 정보 수정
    @PatchMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.MemberInfoRes> updateMemberInfo(
            @RequestHeader("Authorization") String token,
            @RequestBody MemberReqDTO.UpdateReq request) {
        BaseSuccessCode code = MemberSuccessCode.UPDATE_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 선호 음식 선택
    @PostMapping("/v1/users/me/preferences/foods")
    public ApiResponse<Void> setFoodPreferences(
            @RequestHeader("Authorization") String token,
            @RequestBody MemberReqDTO.FoodPreferenceReq request) {
        BaseSuccessCode code = MemberSuccessCode.PREFERENCE_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 진행중/완료 미션 목록 조회
    @GetMapping("/v1/users/me/missions")
    public ApiResponse<MemberResDTO.MissionListRes> getMissions(
            @RequestHeader("Authorization") String token,
            @RequestParam String status) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 본인 리뷰 조회
    @GetMapping("/v1/users/{userId}/reviews")
    public ApiResponse<MemberResDTO.ReviewListRes> getReviews(
            @PathVariable Long userId) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}