package com.sayit.shadhi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@SuppressWarnings("all")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long userId;

    @Column(nullable = false)
    private String password;

    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    private String name;

    private String age;

    @Column(
            updatable = false
    )
    private String gender;

    private String dateOfBirth;

    private String community;

    private String religion;

    private String subCaste;

    private String educationalQualification;

    private String salaryPerAnnum;

    private String authority;

    private String profilePhoto;

    @ElementCollection
    private List<String> galary;

    @OneToOne
    @JoinColumn(name = "chart_id")
    private Chart chart;

    @ManyToMany
    @JoinTable(
            name = "pairs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "paired_user_id")
    )
    @JsonIgnore
    private Set<User> pairs;

    @ManyToMany
    @JoinTable(
            name = "pair_requests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "requested_user_id")
    )
    @JsonIgnore
    private Set<User> pairRequests;

    @OneToMany(mappedBy = "givenUser")
    private Set<ChartRequest> chartRequests;

    @PrePersist
    public void doPrepresist(){
        if (community == null){
            community = "NOT_PREFERED";
        }
        if (salaryPerAnnum == null){
            salaryPerAnnum = "NOT_PREFERED";
        }
        if(subCaste == null){
            subCaste = "NOT_PREFERED";
        }
        if (religion == null){
            religion = "NOT_PREFERED";
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
