package org.example.ensetdemospringangular.services;

import org.example.ensetdemospringangular.entities.Payment;
import org.example.ensetdemospringangular.entities.PaymentStatus;
import org.example.ensetdemospringangular.entities.PaymentType;
import org.example.ensetdemospringangular.entities.Student;
import org.example.ensetdemospringangular.repository.PaymentRepository;
import org.example.ensetdemospringangular.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {

        // creer un path ou on peut sauvegarder le chemin de notre fichier (lendroit=>"user.home") (le nom de repertoire =>"enset-data/payments")
        Path folderPath= Paths.get(System.getProperty("user.home"),"enset-data","payments");
        if(!Files.exists(folderPath)){
            // on utilise createDirectories() si on veut creer dossier a l'interieur d'un autre
            Files.createDirectories(folderPath);
        }

        String filaName= UUID.randomUUID().toString();
        Path filePath= Paths.get(System.getProperty("user.home"),"enset-data","payments",filaName+".pdf");
        Files.copy(file.getInputStream(),filePath);

        Student student=studentRepository.findByCode(studentCode);
        Payment payment=Payment.builder()
                .date(date)
                .type(type)
                .student(student)
                .amount(amount)
                // pour trouver le sous forme de url
                .file(filePath.toUri().toString())
                .build();
        return paymentRepository.save(payment);
    }



    public Payment updatePaymentStatus(PaymentStatus status, Long id){
        Payment payment=paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);

    }

    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }


}
