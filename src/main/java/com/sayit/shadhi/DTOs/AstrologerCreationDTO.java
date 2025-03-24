package com.sayit.shadhi.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AstrologerCreationDTO {
    private String name;
    private String email;
    private int age;
    private int yoe;
    private MultipartFile certrificate;
    private double price;
    private String password;
}
