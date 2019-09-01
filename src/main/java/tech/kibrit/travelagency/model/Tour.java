package tech.kibrit.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tour")
    private TourDetail tourDetails;

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> payments;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TourService> tourServices;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tour_city",
            joinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<City> destinations;
    @ManyToOne
    @JoinColumn(name = "source", referencedColumnName = "id")
    @JsonIgnore
    private City source;

    public Tour() {
    }

    public List<City> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<City> destinations) {
        this.destinations = destinations;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TourDetail getTourDetails() {
        return tourDetails;
    }

    public void setTourDetails(TourDetail tourDetails) {
        this.tourDetails = tourDetails;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<TourService> getTourServices() {
        return tourServices;
    }

    public void setTourServices(List<TourService> tourServices) {
        this.tourServices = tourServices;
    }
}
