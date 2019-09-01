package tech.kibrit.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.kibrit.travelagency.model.MultiSelect;
import tech.kibrit.travelagency.model.Tour;
import tech.kibrit.travelagency.model.TourDetail;
import tech.kibrit.travelagency.model.TourService;
import tech.kibrit.travelagency.repository.TourDetailRepository;
import tech.kibrit.travelagency.repository.TourRepository;
import tech.kibrit.travelagency.repository.TourServiceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TourDetailService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourDetailRepository tourDetailRepository;
    @Autowired
    private TourServiceRepository tourServiceRepository;

    /***********************************************TOUR******************************************/

    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);

    }

    public List<Tour> getAllTour() {
        return tourRepository.findAll();
    }

    public Optional<Tour> getTourId(Long id) {

        return tourRepository.findById(id);
    }

    public void deleteTourById(Long id) {

        tourRepository.deleteById(id);
    }

    public Optional<Tour> updateTour(Tour newTour, Long id) {
        return tourRepository.findById(id).map(tour -> {
            tour.setTitle(newTour.getTitle());
            tour.setTourDetails(newTour.getTourDetails());
            tour.setDestinations(newTour.getDestinations());
            tour.setSource(newTour.getSource());
            return tourRepository.save(tour);
        });
    }

    public Optional<TourDetail> updateTourDetail(TourDetail newTourDetail, Long id) {
        return tourDetailRepository.findById(id).map(tourDetail -> {
            tourDetail.setStartDate(newTourDetail.getStartDate());
            tourDetail.setEndDate(newTourDetail.getEndDate());
            tourDetail.setMaxPassengerCapacity(newTourDetail.getMaxPassengerCapacity());
            tourDetail.setPrice(newTourDetail.getPrice());
            tourDetail.setTour(newTourDetail.getTour());
            tourDetail.setTourStatus(newTourDetail.getTourStatus());
            return tourDetailRepository.save(tourDetail);
        });
    }

    /***********************************************SERVICE******************************************/
    public TourService createService(TourService tourService, Long id) {
        Optional<Tour> optional = tourRepository.findById(id);
        if (optional.isPresent()) {
            Tour tour = optional.get();
            tourService.setTour(tour);
            tour.getTourServices().add(tourService);
            if (tourService.getServicePrice() != null)
                tourService.setPriceInclude(false);
            return tourServiceRepository.save(tourService);
        }
        return tourService;
    }

    public List<TourService> getAllTourService() {

        return tourServiceRepository.findAll();
    }

    public Optional<TourService> getServiceById(Long id) {
        return tourServiceRepository.findById(id);
    }

    public void deleteServiceById(Long id) {
        tourServiceRepository.deleteById(id);
    }


    public Optional<TourService> updateTourService(TourService newTourService, Long id) {
        return tourServiceRepository.findById(id).map(tourService1 -> {
            tourService1.setServiceName(newTourService.getServiceName());
            tourService1.setType(newTourService.getType());
            tourService1.setWebpage(newTourService.getWebpage());
            tourService1.setServicePrice(newTourService.getServicePrice());
            tourService1.setServiceDetails(newTourService.getServiceDetails());
            return tourServiceRepository.save(tourService1);
        });

    }

    /***********************************************SEARCH******************************************/

//    public List<Tour> getFilteredTour(List<Long> ids, Double minPrice, Double maxPrice, Date startDate, Date endDate) {
//        return tourRepository.findByCityPriceAndDate(ids, ids != null && !ids.isEmpty() ? true : null, minPrice, maxPrice, startDate, endDate);
    public List<Tour> getFilteredTour(MultiSelect multiSelect) {
        return tourRepository.findByCityPriceAndDate(multiSelect.getIds(), multiSelect.getCheckIds(), multiSelect.getMinPrice(),
                multiSelect.getMaxPrice(), multiSelect.getStartDate(), multiSelect.getEndDate());
    }


    public List<Tour> toursByDestinations(MultiSelect multiSelect) {
        return tourRepository.toursByDestinations(multiSelect.getIds());
    }
}
