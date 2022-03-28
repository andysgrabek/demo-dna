package com.andysgrabek.dna.joboffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository<ID> extends JpaRepository<JobOffer, ID> {


}