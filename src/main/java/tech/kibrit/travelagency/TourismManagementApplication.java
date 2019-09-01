package tech.kibrit.travelagency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.kibrit.travelagency.model.Role;
import tech.kibrit.travelagency.model.User;
import tech.kibrit.travelagency.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
public class TourismManagementApplication /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        SpringApplication.run(TourismManagementApplication.class, args);

    }

//    @Autowired
//    private UserRepository userRepository;

//    private void createuser(){
//        User user = new User();
//        user.setName("test");
//        user.setPassword("Aa123$123") ;
//        user.setRole(Role.ADMIN);
//        userRepository.save(user);
//    }

//    @Override
//    public void run(String... args) throws Exception {
////        createuser();
//    }
}
