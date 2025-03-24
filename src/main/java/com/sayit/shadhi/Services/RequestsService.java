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


    @Transactional
    public GeneralStatus rejectRequestToPair(long pairId , UserDetails userDetails) {
        Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
        User user = userOptional.get();
        user.getPairRequests().remove(userRepository.findById(pairId).get());
        return GeneralStatus.REJECTED;
    }

    @Transactional
    public GeneralStatus acceptPairRequest(long pairId , UserDetails userDetails) {
//        Optional<User> pairOptional = userRepository.findById(pairId);
//        User user = pairOptional.get();
//        user.getPairRequests().remove(userRepository.findByEmail(userDetails.getUsername()).get());
//        user.getPairs().add(userRepository.findByEmail(userDetails.getUsername()).get());

        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        Optional<User> pairUser = userRepository.findById(pairId);
        User pair  = pairUser.get();
        User userEntity = user.get();
        userEntity.getPairs().add(pair);
        pair.getPairs().add(userEntity);
        return GeneralStatus.ACCEPTED;
    }

    public List<PairResponseDTO> getAllPairRequest(UserDetails userDetails) {
        return userRepository.getAllPairRequests(userDetails.getUsername());
    }



    public List<PairResponseDTO> getAllPairsForthePerson(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        return userRepository.getAllPairsForUser(userDetails.getUsername());
    }


    @Transactional
    public GeneralStatus unPairaPair(Long userId , UserDetails userDetails){
        Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
        User user = userOptional.get();
        user.getPairs().remove(userRepository.findById(userId).get());
        return GeneralStatus.REMOVED;
    }
}
