package tech.kibrit.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class TourService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    @Enumerated(EnumType.STRING)
    private ServiceType type;
    private String webpage;
    private String serviceDetails;
    private  Double servicePrice;
    private boolean priceInclude;
    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    @JsonIgnore
    private Tour tour;
    public TourService() {
    }

    public boolean isPriceInclude() {
        return priceInclude;
    }

    public void setPriceInclude(boolean priceInclude) {
        this.priceInclude = priceInclude;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
