package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chart_rating")
public class ChartRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private Double rating;

    @OneToOne
    private Astrologer astrologer;

    @ManyToOne
    private ChartRequest chartRequest;

    @Override
    public int hashCode() {
        return Objects.hash(ratingId);
    }
}
