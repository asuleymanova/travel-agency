package tech.kibrit.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibrit.travelagency.model.TravelAgency;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long> {

}
