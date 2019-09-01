package tech.kibrit.travelagency.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.kibrit.travelagency.service.UserService;

import java.util.List;
import java.util.Optional;
import tech.kibrit.travelagency.model.Tour;
import tech.kibrit.travelagency.model.User;
import tech.kibrit.travelagency.model.UserResponse;
@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = org.apache.log4j.Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /***********************************************USER******************************************/
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody UserResponse userResponse) {
        return userService.createUser(userResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> showAll() {
        logger.info("Retrieves all the users from database");
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{user_id}")
    public Optional<User> getById(@PathVariable("user_id") Long userId) {

        return userService.getById(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{user_id}/tours")
     public List<Tour> toursByUser(@PathVariable("user_id") Long userId) {

        return userService.toursByUser(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/{user_id}")
    Optional<User> updateUser(@RequestBody User user, @PathVariable("user_id") Long id) {

        return userService.updateUser(user, id);
    }



    /***********************************************SEARCH******************************************/

    @GetMapping(value = "/toursbyuser")
    public List<Tour> findToursByUser() {
        return userService.findToursByUser();
    }

    @GetMapping(value = "/toursbyagency")
    public List<Tour> findToursByAgency() {
        return userService.findToursByAgency();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/tours/cancel/{tour_id}")
    void cancelTour(@PathVariable("tour_id") Long id) {
        userService.cancelTour(id);
    }

//    @GetMapping(value = "/city/search", params = "ids")
//    @ResponseBody
//    public List<Tour> findTourByDestinations(@RequestParam List<Long> ids) {
//        return userService.findTourByDestinations(ids);
//    }


}
