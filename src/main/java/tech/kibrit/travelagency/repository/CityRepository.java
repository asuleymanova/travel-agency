package tech.kibrit.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibrit.travelagency.model.City;


public interface CityRepository extends JpaRepository<City, Long> {


}
