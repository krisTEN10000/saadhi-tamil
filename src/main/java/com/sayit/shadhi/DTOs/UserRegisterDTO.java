package com.sayit.shadhi.DTOs;

public record UserRegisterDTO(
        String password,
        String email,
        String name,
        String age,
        String gender,
        String dateOfBirth,
        String community,
        String religion,
        String subCaste,
        String educationalQualification,
        String salaryPerAnnum,
        String authority,
        String profilePhoto
) {
}
