package tech.kibrit.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.kibrit.travelagency.model.Status;
import tech.kibrit.travelagency.model.TravelAgency;
import tech.kibrit.travelagency.repository.TravelAgencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TravelAgencyService {
    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    public TravelAgency create(TravelAgency travelAgency) {

        travelAgency.setStatus(Status.ACTIVE);
        return travelAgencyRepository.save(travelAgency);
    }

    public List<TravelAgency> showAll() {
        return travelAgencyRepository.findAll();
    }

    public Optional<TravelAgency> findById(Long id) {
        return travelAgencyRepository.findById(id);
    }

    public void deleteByID(Long id) {
        travelAgencyRepository.deleteById(id);
    }

    public Optional<TravelAgency> updateAgency(TravelAgency newAgency, Long id){
        return travelAgencyRepository.findById(id).map(travelAgency -> {
            travelAgency.setAgencyName(newAgency.getAgencyName());
            travelAgency.setContactPerson(newAgency.getContactPerson());
            travelAgency.setWebsite(newAgency.getWebsite());
            travelAgency.setEmail(newAgency.getContactNumber());
            travelAgency.setContactNumber(newAgency.getContactNumber());
            return travelAgencyRepository.save(travelAgency);
        });
    }


}
