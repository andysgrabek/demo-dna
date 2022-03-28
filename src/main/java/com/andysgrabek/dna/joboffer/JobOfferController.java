package com.andysgrabek.dna.joboffer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/offer")
@RestController
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @PostMapping
    public JobOfferDto postOffer(@RequestBody JobOfferDto jobOffer) {
        return jobOfferService.createOffer(jobOffer);
    }

    @GetMapping
    public List<JobOfferDto> getOffers(@RequestParam(defaultValue = "") String employerName,
                                    @RequestParam(defaultValue = "") Integer category) {
        return jobOfferService.findAll(employerName, category);
    }


}
