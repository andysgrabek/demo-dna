package com.andysgrabek.dna;

import com.andysgrabek.dna.joboffer.JobOfferController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobOfferControllerTest {

    @Autowired
    private JobOfferController jobOfferController;

    @Test
    public void testGetAllOffers() {
        Assertions.assertDoesNotThrow(() -> jobOfferController.getOffers("Andrzej", 1));
    }

}
