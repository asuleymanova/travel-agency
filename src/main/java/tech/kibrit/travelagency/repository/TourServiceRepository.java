package tech.kibrit.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibrit.travelagency.model.TourService;


public interface TourServiceRepository extends JpaRepository<TourService, Long> {

}
