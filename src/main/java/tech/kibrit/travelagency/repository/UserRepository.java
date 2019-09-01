package tech.kibrit.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.kibrit.travelagency.model.Tour;
import tech.kibrit.travelagency.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
   User getByUsername(String name);
}
