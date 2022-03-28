package com.andysgrabek.dna.joboffer;

import com.andysgrabek.dna.user.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
public class JobOfferController {

    private final JobOfferRepository jobOfferRepository;


    public JobOfferController(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @PostMapping(value = "/offer")
    public JobOffer postOffer(@RequestBody JobOffer jobOffer) {
        return jobOfferRepository.saveAndFlush(jobOffer);
    }

    @GetMapping(value = "/offer")
    public List<JobOfferDto> getOffers(@RequestParam(defaultValue = "") String employerName,
                                    @RequestParam(defaultValue = "") Integer category) {
        //convert to proper query
        return jobOfferRepository
                .findAll()
                .stream()
                .filter(offer -> Objects.isNull(employerName) || !employerName.isEmpty() || offer
                        .getUser()
                        .getName().toLowerCase(Locale.ROOT)
                        .contains(employerName.toLowerCase(Locale.ROOT)))
                .filter(offer -> Objects.isNull(category) || Objects.equals(category, offer.getCategory().ordinal()))
                .map(x -> new JobOfferDto(x.getId(), x.getCategory(), x.getStartDate(), x.getEndDate(),
                        new UserDto(x.getUser().getId(), x.getUser().getName(), x.getUser().getCreationDate())))
                .toList();
    }


}
