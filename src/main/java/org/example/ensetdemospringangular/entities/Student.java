package org.example.ensetdemospringangular.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    // code deverais etre unique
    @Column(unique = true)
    private String code;

    private String programId;
    private String photo;



}
