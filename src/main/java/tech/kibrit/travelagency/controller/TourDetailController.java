package tech.kibrit.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.kibrit.travelagency.model.MultiSelect;
import tech.kibrit.travelagency.model.Tour;
import tech.kibrit.travelagency.model.TourService;
import tech.kibrit.travelagency.service.TourDetailService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
public class TourDetailController {
    @Autowired
    private TourDetailService tourDetailService;

    /***********************************************Tour******************************************/
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public Tour createTour(@RequestBody Tour tour) {
        return tourDetailService.createTour(tour);
    }

    @GetMapping
    public List<Tour> showAllTour() {
        return tourDetailService.getAllTour();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/{tour_id}")
    public Optional<Tour> getTourId(@PathVariable("tour_id") Long tourId) {

        return tourDetailService.getTourId(tourId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping(value = "/{tour_id}")
    public void deleteTourById(@PathVariable("tour_id") Long tourId) {

        tourDetailService.deleteTourById(tourId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(value = "/{tour_id}")
    Optional<Tour> updateTour(@RequestBody Tour tour, @PathVariable("tour_id") Long id) {
        return tourDetailService.updateTour(tour, id);
    }

    /***********************************************SERVICE******************************************/
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/services/{tour_id}")
    public TourService createService(@RequestBody TourService tourService, @PathVariable("tour_id") Long id) {
        return tourDetailService.createService(tourService, id);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping(value = "/services")
    List<TourService> showAllTourService() {
        return tourDetailService.getAllTourService();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/services/{service_id}")
    public Optional<TourService> getServiceById(@PathVariable("service_id") Long id) {

        return tourDetailService.getServiceById(id);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping(value = "/services/{service_id}")
    public void deleteServiceById(@PathVariable("service_id") Long id) {
        tourDetailService.deleteServiceById(id);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping(value = "/services/{service_id}")
    Optional<TourService> updateTourService(@RequestBody TourService tourService, @PathVariable("service_id") Long id) {

        return tourDetailService.updateTourService(tourService, id);
    }

    /***********************************************SEARCH******************************************/
//    @GetMapping(value = "/search")
//    public List<Tour> getFilteredTour(@RequestParam(value = "city_id", required = false) List<Long> ids,
//                                      @RequestParam("price_from") Double tourDetailMinPrice,
//                                      @RequestParam("price_to") Double tourDetailMaxPrice,
//                                      @RequestParam("date_from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tourDetailStartDate,
//                                      @RequestParam("date_to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tourDetailEndDate) {
//        return tourDetailService.getFilteredTour(ids, tourDetailMinPrice, tourDetailMaxPrice, tourDetailStartDate, tourDetailEndDate);
//    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<Tour> getFilteredTour(@RequestBody MultiSelect multiSelect) {
        return tourDetailService.getFilteredTour(multiSelect);
    }


    @PostMapping(value = "/cities")
    @ResponseBody
    public List<Tour> toursByDestinations(@RequestBody MultiSelect multiSelect){
        return tourDetailService.toursByDestinations(multiSelect);
    }

}
