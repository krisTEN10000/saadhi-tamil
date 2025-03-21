package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.DTOs.AstologerResponseDTO;
import com.sayit.shadhi.DTOs.PersonRequestDTO;
import com.sayit.shadhi.DTOs.PersonResultDTO;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Security.Pojo.UserPOJO;
import com.sayit.shadhi.Services.AstrologyService;
import com.sayit.shadhi.Services.SearchService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final AstrologyService astrologyService;

    @GetMapping("/get/pair")
    public ResponseEntity<List<PersonResultDTO>> searchPair(@RequestBody PersonRequestDTO personRequestDTO , @ModelAttribute("userId") UserDetails user){
        return ResponseEntity.ok().body(searchService.getMatchedPairForFilter(personRequestDTO));
    }

    @GetMapping("/get/astrologer")
    public ResponseEntity<List<AstologerResponseDTO>> getAstrologer(){
        return ResponseEntity.ok().body(searchService.getAllAstrologers());
    }

    @PostMapping("/give/request/ast")
    public ResponseEntity<?> giveRequestToAstrologer(@RequestParam String userName ){
        return ResponseEntity.ok().body("");
    }

}
