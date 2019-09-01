package tech.kibrit.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.kibrit.travelagency.model.*;
import org.springframework.stereotype.Service;
import tech.kibrit.travelagency.repository.TourDetailRepository;
import tech.kibrit.travelagency.repository.TourRepository;
import tech.kibrit.travelagency.repository.TourServiceRepository;
import tech.kibrit.travelagency.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourDetailRepository tourDetailRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TourServiceRepository tourServiceRepository;

    private TourDetail tourDetail;

    /***********************************************USER******************************************/


    public User createUser(UserResponse userResponse) {
        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(userResponse.getPassword()));
        if (userRepository.findByUsername(userResponse.getUsername()) == null) {
            user.setUsername(userResponse.getUsername());
            return userRepository.save(user);
        }
        return user;
    }

    public List<User> getAll() {

        return userRepository.findAll();

    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public List<Tour> toursByUser(Long id) {
        Optional<User> optional = userRepository.findById(id);

            return  optional.get().getTours();

    }


    public Optional<User> updateUser(User newUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setSurname((newUser.getSurname()));
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            return userRepository.save(user);
        });
    }


    /***********************************************QUERIES******************************************/
    @Scheduled(cron = "* */1 * * * ?")
    public void scheduleTime() {
       List<TourDetail> tourDetails = tourDetailRepository.findExpiredTours();
       tourDetails.forEach(tourDetail-> {
                   if (tourDetail != null) {
                       tourDetail.setTourStatus(TourStatus.EXPIRED);
                       tourDetailRepository.save(tourDetail);
                   }
               }
        );
        }


    public void cancelTour(Long id) {
        TourDetail tourDetail = tourDetailRepository.findCancelledTours(id);
        tourDetail.setTourStatus(TourStatus.CANCELLED);
        tourDetailRepository.save(tourDetail);
    }


    /***********************************************AUTHENTICATION******************************************/

//public User getCurrentAuthenticatedUser() {
//    User user;
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication == null || !authentication.isAuthenticated()) {
//        return null;
//    }
//    return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
//}
    public User getCurrentAuthenticatedUser() {

        return userRepository.getByUsername(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName());
    }

    public List<Tour> findToursByAgency() {
        User user = getCurrentAuthenticatedUser();
        return tourRepository.findToursByAgency(user.getTravelAgency().getId());


    }

    public List<Tour> findToursByUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUser = authentication.getName();
        User user = getCurrentAuthenticatedUser();
        return tourRepository.findToursByUser(user.getId());

    }

    /***********************************************ENCODE******************************************/

    public static void main(String[] args) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        System.out.println(bc.encode("aArzu1234$"));


    }


}

