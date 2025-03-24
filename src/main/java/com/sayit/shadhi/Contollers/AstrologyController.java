package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.DTOs.AstrologerPriceFilter;
import com.sayit.shadhi.DTOs.ChartRequestDTO;
import com.sayit.shadhi.DTOs.ChartScoreDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Security.Authentication.ContextImplementation;
import com.sayit.shadhi.Services.AstrologyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/astrology")
public class AstrologyController {

    private final AstrologyService astrologyService;

    @GetMapping("/get/documents")
    public ResponseEntity<?> getAstrologyDocument(@RequestBody ChartRequestDTO chartRequestDTO){
        return ResponseEntity.ok().body(astrologyService.getChartdocuments(chartRequestDTO));
    }

    @PostMapping("/give-score")
    public ResponseEntity<String> giveScoreToUsers(@RequestBody ChartScoreDTO chartScoreDTO){
            return ResponseEntity.ok().body("Score Successfully updated");
    }

    @GetMapping("/get/all-astrologer")
    public ResponseEntity<List<Astrologer>> getAllAstrologer(@RequestAttribute("userDetails") UserDetails userDetails){
        return ResponseEntity.ok().body(astrologyService.getAllAstrologer());
    }

    @GetMapping("/get/price-range")
    public ResponseEntity<List<Astrologer>> getAstrologerBetweenThePriceRange(@RequestBody AstrologerPriceFilter astrologerPriceFilter){
        return ResponseEntity.ok().body(astrologyService.getAstrologerByRange(astrologerPriceFilter));
    }

    @GetMapping("/get/filter")
    public ResponseEntity<List<Astrologer>> getAstrologerFilter(){
        return ResponseEntity.ok().body(astrologyService.getAllAstrologer());
    }

    @PutMapping("/put/rating")
    public ResponseEntity<String> putRating(@RequestBody AstrologerPriceFilter astrologerPriceFilter){
        return ResponseEntity.ok().body("Rating Successfully updated");
    }

    @PostMapping("/give/chart/request")
    public ResponseEntity<GeneralStatus> giveChartRequest(@RequestParam long userId , @RequestAttribute("userDetails") UserDetails userDetails , @RequestParam long astrologerId ){
        return  ResponseEntity.ok(astrologyService.giveChartToAstrologer(userId , userDetails, astrologerId));
    }
}
