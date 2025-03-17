package com.sayit.shadhi.DTOs;

public record PersonRequestDTO(
        Integer ageFrom,
        Integer ageTo,
        String gender,
        String community,
        String religion,
        String subCaste,
        String educationalQualification,
        Long salaryStart,
        Long salalryEnd
) {
}
