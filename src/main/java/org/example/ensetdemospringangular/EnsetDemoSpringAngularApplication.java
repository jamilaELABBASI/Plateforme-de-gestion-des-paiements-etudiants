package org.example.ensetdemospringangular;

import org.example.ensetdemospringangular.entities.Payment;
import org.example.ensetdemospringangular.entities.PaymentType;
import org.example.ensetdemospringangular.entities.Student;
import org.example.ensetdemospringangular.repository.PaymentRepository;
import org.example.ensetdemospringangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EnsetDemoSpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetDemoSpringAngularApplication.class, args);
    }

    //pour test
@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){

        return args -> {
                studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                                .firstName("Mohammed").code("112233").programId("SDIA")
                        .build());

                studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                        .firstName("Imane").code("112244").programId("GLSID")
                        .build());


                studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                        .firstName("Yasmine").code("112255").programId("SDIA")
                        .build());


                studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                        .firstName("Najat").code("112266").programId("BDCC")
                        .build());

                PaymentType[] paymentTypes=PaymentType.values();
                Random random=new Random();

                // pour chaque etudiant je vais creer un payment de 10
                studentRepository.findAll().forEach(st->{
                    for(int i=0;i<10;i++){
                        // generer un int entre 0 et paymentTypes.length
                        int index=random.nextInt(paymentTypes.length);
                        Payment payment=Payment.builder()
                                .amount(1000+(int)(Math.random()*20000))
                                .type(paymentTypes[index])
                                .date(LocalDate.now())
                                .student(st)
                                .build();
                        paymentRepository.save(payment);
                    }
                });






        };
    }

}
