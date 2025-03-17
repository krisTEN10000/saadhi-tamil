package com.sayit.shadhi.DTOs;

public record PersonResultDTO(
        String name,
        String age,
        String gender,
        String dateOfBirth,
        String community,
        String religion,
        String subCaste,
        String educationalQualification,
        String salaryPerAnnum
) {
}
