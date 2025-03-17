package com.sayit.shadhi.DTOs;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AstologerResponseDTO {
    private String email;
    private String name;
    private Float rating;
    private Integer YOE;
    private Double price;
}
