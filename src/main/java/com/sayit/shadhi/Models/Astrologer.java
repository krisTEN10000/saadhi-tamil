package com.sayit.shadhi.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
     @JsonBackReference("defaultReference")
     private Set<ChartRequest> chartRequests =  new HashSet<>();

     @Override
     public int hashCode() {
          return Objects.hash(astrologerId);
     }
}
