package com.sayit.shadhi.Utils;

import com.sayit.shadhi.DTOs.AstrologerCreationDTO;
import com.sayit.shadhi.DTOs.UserRegisterDTO;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mappers {

    private final ModelMapper modelMapper;

    public User mapToUser(UserRegisterDTO  user){
        return User.builder()
                .age(user.age())
                .email(user.email())
                .name(user.name())
                .community(user.community())
                .password(user.password())
                .authority(user.authority())
                .educationalQualification(user.educationalQualification())
                .gender(user.gender())
                .profilePhoto(user.profilePhoto())
                .salaryPerAnnum(user.salaryPerAnnum())
                .subCaste(user.subCaste())
                .religion(user.religion())
                .dateOfBirth(user.dateOfBirth())
                .build();
    }


    public Astrologer maptoAstrologer(AstrologerCreationDTO astrologerCreationDTO){
        return  Astrologer
                .builder()
                .YOE(astrologerCreationDTO.getYOE())
                .price(astrologerCreationDTO.getPrice())
                .name(astrologerCreationDTO.getName())
                .password(astrologerCreationDTO.getPassword())
                .age(astrologerCreationDTO.getAge())
                .build();
    }
}
