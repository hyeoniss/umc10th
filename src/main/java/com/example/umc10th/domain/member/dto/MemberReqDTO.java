package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long id
    ) {}

    // 회원가입
    public record SignupReq(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "유효한 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
            String password,

            @NotBlank(message = "닉네임은 필수입니다.")
            String nickname,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber
    ) {}

    // 로그인
    public record LoginReq(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "유효한 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {}

    // 사용자 정보 수정
    public record UpdateReq(
            String nickname,
            String phoneNumber
    ) {}

    // 선호 음식 선택
    public record FoodPreferenceReq(
            @NotEmpty(message = "선호 음식 카테고리는 최소 1개 이상 선택해야 합니다.")
            List<String> foodCategories
    ) {}
}