package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.tutors.domain.TutorProfile;
import pl.tutors.rest.dtos.OfferDTO;
import pl.tutors.rest.dtos.UserDTO;
import pl.tutors.service.offer.OfferService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OffersResource {
    private final OfferService offerService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid OfferDTO offerDTO) {
        offerService.createOffer(offerDTO);
    }

    @RequestMapping(value = "{offerId}/confirmation", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable UUID offerId) {
        offerService.confirmOffer(offerId);
    }
}
