package com.reservas.Reservas_Prueba1.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ContactoController {

    @Autowired
    JavaMailSender javaMailSender;
    
    @PostMapping("/api/contact")
    public void mandarEmail(@RequestBody ContactoFormulario form){

        String nombre=form.getNombre();
        String correo= form.getCorreo();
        String asunto= form.getAsunto();
        String message=form.getMensaje();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo("casarurallosmaragatos@gmail.com");
        simpleMailMessage.setSubject("Nuevo mensaje de contacto");
        simpleMailMessage.setText("Nombre: "+ nombre + "\nCorreo: " + correo  + "\nAsunto " + asunto + "\nMensaje: "+ message);

        javaMailSender.send(simpleMailMessage);
        //COPIA DEL CORREO
        SimpleMailMessage userMail = new SimpleMailMessage();
        userMail.setTo(correo);
        userMail.setSubject("Copia del mensaje de contacto");
        userMail.setText("Gracias por contactarnos. Aquí tienes una copia de tu mensaje:\n\n" + message);

        javaMailSender.send(userMail);
    }
/*
    @GetMapping("/api/contact")
    public ResponseEntity<String> getEcho(){
        return new ResponseEntity<String>("Hola", HttpStatus.OK);
    }
    */

}
