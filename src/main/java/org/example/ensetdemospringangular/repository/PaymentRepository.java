package org.example.ensetdemospringangular.repository;

import org.example.ensetdemospringangular.entities.Payment;
import org.example.ensetdemospringangular.entities.PaymentStatus;
import org.example.ensetdemospringangular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    /*
        je crois jpa genere juste les crud des attributs normaux dans la classe ms pas les attributs de type d'une autre classe
     */
List<Payment> findByStudentCode(String code);
List<Payment> findByStatus(PaymentStatus status);
List<Payment> findByType(PaymentType type);


}
