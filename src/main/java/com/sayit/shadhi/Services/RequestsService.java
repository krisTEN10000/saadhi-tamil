package com.sayit.shadhi.Services;

import com.sayit.shadhi.DTOs.PairResponseDTO;
import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Interfaces.RequestInterface;
import com.sayit.shadhi.Models.Chart;
import com.sayit.shadhi.Models.ChartRequest;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestsService implements RequestInterface {

    private final UserRepository userRepository;

    public void getRequest(){

    }

    @Override
    public AstrologyStatus getStatusOFaPair(long chartId) {
        return null;
    }

    @Override
    @Transactional
    public GeneralStatus giveRequestToPair(long pairId , UserDetails userDetails) {
        Optional<User> pairOptional = userRepository.findById(pairId);
        User user = pairOptional.get();
        Optional<User> giverEntity = userRepository.findByEmail(userDetails.getUsername());
        User userGiver = giverEntity.get();
        userGiver.getPairRequests().add(user);

        return GeneralStatus.SENDED;
    }


    public GeneralStatus rejectRequestToPair(long pairId , UserDetails userDetails) {
        Optional<User> pairOptional = userRepository.findById(pairId);
        User user = pairOptional.get();
        user.getPairRequests().remove(userRepository.findByEmail(userDetails.getUsername()).get());
        return GeneralStatus.REJECTED;
    }

    @Transactional
    public GeneralStatus acceptPairRequest(long pairId , UserDetails userDetails) {
        Optional<User> pairOptional = userRepository.findById(pairId);
        User user = pairOptional.get();
        user.getPairRequests().remove(userRepository.findByEmail(userDetails.getUsername()).get());
        user.getPairs().add(userRepository.findByEmail(userDetails.getUsername()).get());
        return GeneralStatus.ACCEPTED;
    }

    public List<PairResponseDTO> getAllPairRequest(UserDetails userDetails) {
        return userRepository.getAllPairRequests(userDetails.getUsername());
    }

    public GeneralStatus giveChartToAstrologer(long pairId , UserDetails userDetails) {
        ChartRequest chartRequest = new ChartRequest();
        Optional<User> pairOptional = userRepository.findById(pairId);
        User user = pairOptional.get();
        Chart bridechart =  user.getChart();
        Chart groomChart =  userRepository.findByEmail(userDetails.getUsername()).get().getChart();

        return null;

    }

    public List<PairResponseDTO> getAllPairsForthePerson(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        return userRepository.getAllPairsForUser(userDetails.getUsername());
    }


}
