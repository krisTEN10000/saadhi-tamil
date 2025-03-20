package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.DTOs.PairResponseDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Repositories.UserRepository;
import com.sayit.shadhi.Services.RequestsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/request")
@RestController
@AllArgsConstructor
public class RequestController {

    private final RequestsService requestsService;
    private final UserRepository userRepository;

    @PutMapping("/accept")
    public ResponseEntity<GeneralStatus> AcceptRequest(@RequestParam long userId , @RequestAttribute("userDetails")UserDetails userDetails) {
        return ResponseEntity.ok().body(requestsService.acceptPairRequest(userId , userDetails));
    }
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllRequest(@RequestAttribute("userDetails") UserDetails userDetails) {
        return ResponseEntity.ok().body(requestsService.getAllPairRequest(userDetails));
    }
    @PutMapping("/reject")
    public ResponseEntity<GeneralStatus> RejectRequest(@RequestParam long userId , @RequestAttribute("userDetails") UserDetails userDetails) {
        return ResponseEntity.ok().body(requestsService.rejectRequestToPair(userId , userDetails));
    }
    @PutMapping("/birth-chart/")
    public ResponseEntity<String> giveRequestToBirthChart(@RequestParam long userId){
        return null;
    }

    @PutMapping("/give")
    public ResponseEntity<GeneralStatus> giveRequestToGive(@RequestParam long userId ,   @RequestAttribute("userDetails") UserDetails userDetails){
           return ResponseEntity.ok().body(requestsService.giveRequestToPair(userId, userDetails));
    }
    @PostMapping("/give/chart/astrologer")
    public ResponseEntity<GeneralStatus> giveCharToAstroler(@RequestParam Long userId){
        return null;
    }

    @GetMapping("/get/all/pairs")
    public ResponseEntity<List<PairResponseDTO>> getAllPairForThePerson(@RequestAttribute("userDetails") UserDetails userDetails) {
        return ResponseEntity.ok().body(requestsService.getAllPairsForthePerson(userDetails));
    }
    @DeleteMapping("/unpair")
    public ResponseEntity<GeneralStatus> unpairUser(@RequestAttribute("userDetails") UserDetails userDetails ,@RequestParam Long userId){
        return  ResponseEntity.ok(requestsService.unPairaPair(userId , userDetails));
    }

}
