package com.andysgrabek.dna.joboffer;

import com.andysgrabek.dna.user.UserDto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class JobOfferDto implements Serializable {
    private final Long id;
    private final JobOfferCategory category;
    private final Date startDate;
    private final Date endDate;
    private final UserDto user;

    public JobOfferDto(Long id, JobOfferCategory category, Date startDate, Date endDate, UserDto user) {
        this.id = id;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
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

    public UserDto getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOfferDto entity = (JobOfferDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.category, entity.category) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, startDate, endDate, user);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "category = " + category + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ", " +
                "user = " + user + ")";
    }
}
