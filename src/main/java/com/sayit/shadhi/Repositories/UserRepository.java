package com.sayit.shadhi.Repositories;

import com.sayit.shadhi.DTOs.PairResponseDTO;
import com.sayit.shadhi.DTOs.PersonResultDTO;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.Implementation.FilterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    @Query("SELECT new com.sayit.shadhi.DTOs.PersonResultDTO(u.name, u.age, u.gender, u.dateOfBirth, u.community, u.religion, u.subCaste, u.educationalQualification, u.salaryPerAnnum) FROM User u " +
            "WHERE u.age BETWEEN :fromAge AND :toAge " +
            "AND (:community IS NULL OR u.community = :community) " +
            "AND (:subCaste IS NULL OR u.subCaste = :subCaste) " +
            "AND (:startRange IS NULL OR :endRange IS NULL OR u.salaryPerAnnum BETWEEN :startRange AND :endRange) " +
            "AND (:religion IS NULL OR u.religion = :religion)")
    List<PersonResultDTO> getUserBasedOnFilter(
            @Param("fromAge") int fromAge,
            @Param("toAge") int toAge,
            @Param("community") String community,
            @Param("subCaste") String subCaste,
            @Param("startRange") Long startRange,
            @Param("endRange") Long endRange,
            @Param("religion") String religion
    );

    public Optional<User> findByEmail(String email);

    @Query("select new com.sayit.shadhi.DTOs.PersonResultDTO(u.name, u.age, u.gender, u.dateOfBirth, u.community, u.religion, u.subCaste, u.educationalQualification, u.salaryPerAnnum) from User u where u.email = :userName ")
    public Optional<PersonResultDTO> findByUsername(@Param("userName") String username);


    @Query("select  new com.sayit.shadhi.DTOs.PairResponseDTO( p.userId, p.name, p.age, p.salaryPerAnnum, p.community, p.subCaste, p.religion) from User u JOIN u.pairRequests p where u.email = :userName")
    public List<PairResponseDTO> getAllPairRequests(@Param("userName") String userName);

    @Query("select new com.sayit.shadhi.DTOs.PairResponseDTO(p.userId, p.name, p.age, p.salaryPerAnnum, p.community, p.subCaste, p.religion) from User user join user.pairs p where user.email = :userName")
    public List<PairResponseDTO> getAllPairsForUser(@Param("userName") String userName);

}
