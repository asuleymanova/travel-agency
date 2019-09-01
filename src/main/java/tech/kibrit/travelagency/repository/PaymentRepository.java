package tech.kibrit.travelagency.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibrit.travelagency.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}