package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."),
    SIGNUP_OK(HttpStatus.OK,
            "MEMBER200_2",
            "성공적으로 회원가입했습니다."),
    LOGIN_OK(HttpStatus.OK,
            "MEMBER200_3",
            "성공적으로 로그인했습니다."),
    LOGOUT_OK(HttpStatus.OK,
            "MEMBER200_4",
            "성공적으로 로그아웃했습니다."),
    DELETE_OK(HttpStatus.OK,
            "MEMBER200_5",
            "성공적으로 회원탈퇴했습니다."),
    UPDATE_OK(HttpStatus.OK,
            "MEMBER200_6",
            "성공적으로 정보를 수정했습니다."),
    PREFERENCE_OK(HttpStatus.OK,
            "MEMBER200_7",
            "성공적으로 선호 음식을 설정했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}