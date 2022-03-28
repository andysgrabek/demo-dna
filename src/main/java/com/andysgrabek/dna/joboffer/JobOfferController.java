package com.andysgrabek.dna.joboffer;

import com.andysgrabek.dna.user.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @PostMapping(value = "/offer")
    public JobOfferDto postOffer(@RequestBody JobOfferDto jobOffer) {
        return jobOfferService.createOffer(jobOffer);
    }

    @GetMapping(value = "/offer")
    public List<JobOfferDto> getOffers(@RequestParam(defaultValue = "") String employerName,
                                    @RequestParam(defaultValue = "") Integer category) {
        return jobOfferService.findAll(employerName, category);
    }


}
