package org.example.ensetdemospringangular.dtos;

import jakarta.persistence.*;
import lombok.*;
import org.example.ensetdemospringangular.entities.PaymentStatus;
import org.example.ensetdemospringangular.entities.PaymentType;
import org.example.ensetdemospringangular.entities.Student;

import java.time.LocalDate;


// c'est pas une entirie jpa
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {

    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;

}
