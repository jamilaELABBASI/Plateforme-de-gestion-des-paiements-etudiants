package org.example.ensetdemospringangular.web.controller;

import org.example.ensetdemospringangular.entities.Payment;
import org.example.ensetdemospringangular.entities.PaymentStatus;
import org.example.ensetdemospringangular.entities.PaymentType;
import org.example.ensetdemospringangular.entities.Student;
import org.example.ensetdemospringangular.repository.PaymentRepository;
import org.example.ensetdemospringangular.repository.StudentRepository;
import org.example.ensetdemospringangular.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")

public class PaymentRestConstroller {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestConstroller(StudentRepository studentRepository,PaymentRepository paymentRepository){
            this.studentRepository=studentRepository;
            this.paymentRepository=paymentRepository;
    }

    // il faut copier toutes les methodes qui sont la dans service et lever les annotation faire la meme chose que les autres methodes qui sont deja creer avant

    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/paymentsByStatus")
    public List<Payment> paymentByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @PutMapping("payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){

        return paymentService.updatePaymentStatus(status,id);

    }

    @GetMapping("/paymentsByType")
    public List<Payment> PaymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);

    }

    @GetMapping("/payment/{id}")
    //@PathVariable  on va recuperer l'id a partie l'url
    public Payment getPaymentById(@PathVariable Long id){

        // get()  on supposse qu'il existe
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students")

    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    /*
      si on utilise     @GetMapping("/students/{programId}")
      spring va confronter une confusion parcequ'il s'agit pour lui meme path que
     @GetMapping("/student/{code}")
    */

    @GetMapping("/studentsByProgramId/{programId}")
    // on utilise @RequestBody si on pas utiliser id dans le chemin
    public List<Student> getStudentsByProgramId(@PathVariable String programId){
        return studentRepository.findByProgramId(programId);
    }
        /* MultipartFile faire upload
            on met path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE
            pour dire a spring que les donnees contient fichier
         */
    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        return this.paymentService.savePayment(file,date,amount,type,studentCode);
    }

    // indiquer que c'est un fichier pdf
@GetMapping(path = "paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
public byte[] getPaymentFile( Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
}




}
