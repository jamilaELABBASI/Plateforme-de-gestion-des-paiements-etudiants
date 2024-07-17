package org.example.ensetdemospringangular.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ensetdemospringangular.entities.Payment;
import org.example.ensetdemospringangular.entities.PaymentType;

import java.time.LocalDate;

// on a utiliser cette class parceque quand on veut creer un payment on doit envoyer plusieurs parametres au niveau d'URL n'est pas pratique et de preference utilise cette maniere
@Getter  @Setter  @NoArgsConstructor @AllArgsConstructor
public class NewPaymentDTO {

    private double amount;
    private PaymentType type;
    public LocalDate date;
    private String studentCode;
}
