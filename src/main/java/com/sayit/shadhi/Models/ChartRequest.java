package com.sayit.shadhi.Models;

import com.sayit.shadhi.Enums.AstrologyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "chartRequest")
@NoArgsConstructor
@Builder
public class ChartRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestID;

    @ManyToOne
    private User givenUser;

    @OneToOne
    private User pair;

    @OneToMany(mappedBy = "chartRequest")
    private List<ChartRating> chartRating;

    @ManyToMany
    @JoinTable(
            name = "chartrequest_astrologer",
            joinColumns = @JoinColumn(name = "astrologer_id"),
            inverseJoinColumns = @JoinColumn(name = "chartrequest_id")
    )
    private List<Astrologer> astrologers;

}
