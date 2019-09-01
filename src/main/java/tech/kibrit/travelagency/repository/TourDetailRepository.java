package tech.kibrit.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.kibrit.travelagency.model.TourDetail;

import java.sql.Date;
import java.util.List;

public interface TourDetailRepository extends JpaRepository<TourDetail, Long> {
    @Query("select td from TourDetail td where td.endDate<current_timestamp")
    List<TourDetail>findExpiredTours();

    @Query("select td from TourDetail td where td.id=:id")
    TourDetail findCancelledTours(@Param("id") Long id);
}
