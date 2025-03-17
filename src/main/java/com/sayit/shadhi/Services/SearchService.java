package com.sayit.shadhi.Services;

import com.sayit.shadhi.DTOs.AstologerResponseDTO;
import com.sayit.shadhi.DTOs.PersonRequestDTO;
import com.sayit.shadhi.DTOs.PersonResultDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Models.ChartRequest;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final AstrologerRepository astrologerRepository;
    public List<PersonResultDTO> getMatchedPairForFilter(PersonRequestDTO personRequestDTO){
        return userRepository.getUserBasedOnFilter(
                personRequestDTO.ageFrom(),
                personRequestDTO.ageTo(),
                personRequestDTO.community(),
                personRequestDTO.subCaste(),
                personRequestDTO.salaryStart(),
                personRequestDTO.salalryEnd(),
                personRequestDTO.religion()
        );
    }

    public List<AstologerResponseDTO> getAllAstrologers() {
        return astrologerRepository.findAllAstrologers();
    }

    @Transactional
    public GeneralStatus giveAstrologerAChart(@ModelAttribute("userId")UserDetails userDetails){
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        user.get()
                .setPairRequests();
    }
}
