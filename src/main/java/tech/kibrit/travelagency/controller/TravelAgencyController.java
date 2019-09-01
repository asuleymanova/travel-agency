package tech.kibrit.travelagency.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.kibrit.travelagency.model.TravelAgency;
import tech.kibrit.travelagency.service.TravelAgencyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travelagencies")
public class TravelAgencyController {

     Logger logger = LogManager.getLogger(TravelAgencyController.class);
    @Autowired
    private TravelAgencyService travelAgencyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public TravelAgency create(@RequestBody TravelAgency travelAgency) {

        return travelAgencyService.create(travelAgency);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<TravelAgency> showAll() {

        logger.info("All Travel agencies listed");
        return travelAgencyService.showAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{agency_id}")
    public Optional<TravelAgency> getById(@PathVariable("agency_id") Long agencyId) {

        return travelAgencyService.findById(agencyId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/{agency_id}")
    Optional<TravelAgency> updateAgency(@RequestBody TravelAgency travelAgency, @PathVariable("agency_id") Long id) {

        return travelAgencyService.updateAgency(travelAgency, id);
    }


}
