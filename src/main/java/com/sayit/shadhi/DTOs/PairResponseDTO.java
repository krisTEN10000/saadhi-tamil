package com.sayit.shadhi.DTOs;

public record PairResponseDTO(
        Long userId ,
        String name,
        String age,
        String salaryPerAnnum,
        String caste ,
        String subCaste,
        String religion
) {
}
