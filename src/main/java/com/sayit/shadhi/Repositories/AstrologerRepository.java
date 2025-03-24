package com.sayit.shadhi.Repositories;


import com.sayit.shadhi.DTOs.AstologerResponseDTO;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.ChartRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AstrologerRepository extends JpaRepository<Astrologer , Long> {

     @Query(value = "SELECT a FROM Astrologer WHERE a.price BETWEEN :startFrom AND :endAt" , nativeQuery = true)
     public List<Astrologer> getAllAstrologerBetweenTheRange(
             @Param("startFrom") double startfrom ,@Param("endAt") double endAt
     );
     public Optional<Astrologer> findByEmail(String email);

     @Query("select new com.sayit.shadhi.DTOs.AstologerResponseDTO(astrologer.email , astrologer.name , astrologer.averageRating , astrologer.YOE , astrologer.price) from Astrologer astrologer")
     public List<AstologerResponseDTO> findAllAstrologers();

//@Query("select new com.sayit.shadhi.DTOs.ChartResponseDTO( new com.sayit.shadhi.DTOs.PersonResultDTO(chart.givenUser.name , chart.givenUser.)) from Astrologer astro join astro.chartRequests chart where astro.astrologerId = :astrologerId")
//     public List<ChartRequest> getChartRequestsOfAstrologer(@Param("astrologerId") Astrologer id);


}
