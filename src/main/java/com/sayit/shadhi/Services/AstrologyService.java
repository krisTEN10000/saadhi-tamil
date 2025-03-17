package com.sayit.shadhi.Services;

import com.sayit.shadhi.DTOs.AstrologerPriceFilter;
import com.sayit.shadhi.DTOs.AstrologerRatingDTO;
import com.sayit.shadhi.DTOs.ChartRequestDTO;
import com.sayit.shadhi.DTOs.ChartScoreDTO;
import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Exceptions.SomethingWentWorngException;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.ChartRequest;
import com.sayit.shadhi.Models.ChartRating;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Repositories.ChartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AstrologyService {

    private final ChartRepository chartRepository;
    private final AstrologerRepository astrologerRepository;

    @Transactional
    public String getChartdocuments(ChartRequestDTO chartRequestDTO)throws ChartNotFoundException {
        List<InputStream> documents =  new ArrayList<>();
        Optional<ChartRequest> chartRequest =  chartRepository.findById(chartRequestDTO.getChartRequestID());
        if(chartRequest.isPresent()){
        }else {
         throw new ChartNotFoundException("Chart not in present for users");
        }
        return "documents";
    }

    private void giveInformation(long brideID , long groomID ,  String message){

    }

    @Transactional
    public GeneralStatus setScoreBYAstrologer(ChartScoreDTO chartScoreDTO , String userName)throws ChartNotFoundException{
        Optional<ChartRequest> chartRequest = chartRepository.findById(chartScoreDTO.getChartId());
        if (chartRequest.isPresent()){
           ChartRequest chartData = chartRequest.get();
           chartData.getChartRating().add(
                   ChartRating
                           .builder()
                           .astrologer(astrologerRepository.findByEmail(userName).get())
                           .rating(chartScoreDTO.getScore())
                           .build()
           );
           return GeneralStatus.UPDATED;
        }else {
            throw new ChartNotFoundException("Chart not found in this id");
        }
    }

    public List<Astrologer> getAllAstrologer(){
         return astrologerRepository.findAll();
    }

    public List<Astrologer> getAstrologerByRange(AstrologerPriceFilter astrologerPriceFilter){
         return astrologerRepository.getAllAstrologerBetweenTheRange(astrologerPriceFilter.getStartFrom() , astrologerPriceFilter.getEndAt());
    }

    public List<ChartRequest> getChartRequest(long astrologerId)throws ChartNotFoundException{
         List<ChartRequest> chartRequestList = chartRepository.getChartsOfTheAstrologer(astrologerId);
         if (chartRequestList.isEmpty()){
              throw new ChartNotFoundException("chart not available for you");
         }else {
              return chartRequestList;
         }
    }

    @Transactional
    public String giveRatingToAstrologer(AstrologerRatingDTO astrologerRating){
        Optional<Astrologer>  astrologer = astrologerRepository.findById(astrologerRating.id());
        if (astrologer.isPresent()){
            Astrologer astrologerData = astrologer.get();
            astrologerData.setAverageRating(astrologerData.getAverageRating() + astrologerRating.rating());
            astrologerData.setNumberOfPersonGivesRating(astrologerData.getNumberOfPersonGivesRating() + 1);
            return "Rating given successfully";
        }
        throw new SomethingWentWorngException("Something went wrong");
    }

    public String giveAChartRequestForAstrologer(){
        return null;
    }
}
