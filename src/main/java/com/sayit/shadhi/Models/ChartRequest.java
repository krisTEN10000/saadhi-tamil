package com.sayit.shadhi.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sayit.shadhi.Enums.AstrologyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    private Set<ChartRating> chartRating = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "chartrequest_astrologer",
            joinColumns = @JoinColumn(name = "astrologer_id"),
            inverseJoinColumns = @JoinColumn(name = "chartrequest_id")
    )
    @JsonBackReference("defaultReference")
    private Set<Astrologer> astrologers =  new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(requestID);
    }
}
