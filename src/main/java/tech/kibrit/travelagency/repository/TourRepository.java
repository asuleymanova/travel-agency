package tech.kibrit.travelagency.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.kibrit.travelagency.model.Tour;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("select distinct t from Tour t join t.destinations d where " +
            " (:checkIds is null or d.id in :ids )" +
            " and (t.tourDetails.price between :tourDetailMinPrice and :tourDetailMaxPrice)" +
            " and(t.tourDetails.startDate >=:startDate and t.tourDetails.endDate <=:endDate)")
    List<Tour> findByCityPriceAndDate(@Param("ids") List<Long> ids,
                                      @Param("checkIds") Boolean checkIds,
                                      @Param("tourDetailMinPrice") Double tourDetailMinPrice,
                                      @Param("tourDetailMaxPrice") Double tourDetailMaxPrice,
                                      @Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate);

    @Query("select t from Tour t where t.user.id=:id")
    List<Tour> findToursByUser(@Param("id") Long id);

    @Query("select t from Tour t where t.user.travelAgency.id=:id")
    List<Tour> findToursByAgency(@Param("id") Long id);

    @Query(value = "select distinct t from Tour t join t.destinations d where d.id in(:ids)")
    List<Tour> toursByDestinations(@Param("ids") List<Long> ids);

    Optional<Tour> findById(Long id);


}