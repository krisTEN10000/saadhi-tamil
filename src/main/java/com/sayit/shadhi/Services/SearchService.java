package com.sayit.shadhi.Services;

import com.sayit.shadhi.DTOs.AstologerResponseDTO;
import com.sayit.shadhi.DTOs.PersonRequestDTO;
import com.sayit.shadhi.DTOs.PersonResultDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.ChartRating;
import com.sayit.shadhi.Models.ChartRequest;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Repositories.ChartRequestRepository;
import com.sayit.shadhi.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final AstrologerRepository astrologerRepository;
    private final ChartRequestRepository chartRequestRepository;

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
    public GeneralStatus giveAstrologerAChart(UserDetails userDetails , String pairId , Long AstrologerId){
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        Optional<User> pair = userRepository.findByEmail(pairId);
        Optional<Astrologer> astrologer = astrologerRepository.findById(AstrologerId);
        Astrologer astro = astrologer.get();
        User chartSender = user.get();
        User pairSender = pair.get();
        ChartRequest chartRequest = ChartRequest
                .builder()
                .givenUser(chartSender)
                .pair(pairSender)
                .build();
        chartRequest.getAstrologers().add(astro);
        astro.getChartRequests().add(chartRequest);
        return GeneralStatus.UPDATED;
    }

    @Transactional
    public GeneralStatus deleteChartRequest(Long chartRequestId) {
        chartRequestRepository.deleteById(chartRequestId);
        return GeneralStatus.DELETED;
    }

    public GeneralStatus addAstrologerToChartRequest(Long astrologerId, Long chartRequestId) {
        ChartRequest chartRequest = chartRequestRepository.getOne(chartRequestId);
        Astrologer astro = astrologerRepository.getOne(astrologerId);
        astro.getChartRequests().add(chartRequest);
        return GeneralStatus.UPDATED;
    }

//    public List<ChartRating> getChartRatings(Long chartRequestId) {
//        ChartRequest chartRequest = chartRequestRepository.getOne(chartRequestId);
//        return chartRequest.getChartRating();
//    }

}
