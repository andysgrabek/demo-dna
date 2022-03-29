package com.andysgrabek.dna.joboffer;

import com.andysgrabek.dna.user.User;
import com.andysgrabek.dna.user.UserDto;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class JobOfferService {

    private final JobOfferRepository<Long> jobOfferRepository;

    public JobOfferService(JobOfferRepository<Long> jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOfferDto createOffer(JobOfferDto jobOffer) throws ConstraintViolationException {
        var offer = jobOfferRepository.saveAndFlush(
                new JobOffer(jobOffer.getCategory(), jobOffer.getStartDate(), jobOffer.getEndDate(), new User(jobOffer.getUser().getId()))
        );
        return new JobOfferDto(offer.getId(), offer.getCategory(), offer.getStartDate(), offer.getEndDate(), new UserDto(
                offer.getUser().getId(), offer.getUser().getName(), offer.getUser().getCreationDate()
        ));
    }

    public List<JobOfferDto> findAll(String employerName, Integer category) {
        //TODO: convert to proper query
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
