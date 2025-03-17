package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Astrologer {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long astrologerId;
     @Column(unique = true)
     private String email;
     private String name;
     private String password;
     @Column(
             unique = true
     )
     private String Certrificate;
     private int YOE;
     private double price;
     private int numberOfPersonGivesRating;
     private int age;
     private Float averageRating;

     @ManyToMany(mappedBy = "astrologers")
     private Set<ChartRequest> chartRequests;

}
