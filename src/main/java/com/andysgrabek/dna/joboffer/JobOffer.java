package com.andysgrabek.dna.joboffer;

import com.andysgrabek.dna.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class JobOffer {

    protected JobOffer() {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private JobOfferCategory category;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user", nullable = false)
    private User user;

    public JobOffer(JobOfferCategory category, Date startDate, Date endDate, User user) {
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public JobOfferCategory getCategory() {
        return category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
