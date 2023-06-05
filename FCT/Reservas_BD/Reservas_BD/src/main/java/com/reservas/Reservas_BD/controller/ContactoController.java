package com.reservas.Reservas_BD.controller;

import com.reservas.Reservas_BD.beans.ContactoFormulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        String mensaje=form.getMensaje();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo("casarurallosmaragatos@gmail.com");
        simpleMailMessage.setSubject("Nuevo mensaje de contacto");
        simpleMailMessage.setText("Nombre: "+ nombre + "\nCorreo: " + correo  + "\nAsunto " + asunto + "\nMensaje: "+ mensaje);

        javaMailSender.send(simpleMailMessage);
        //COPIA DEL CORREO
        SimpleMailMessage userMail = new SimpleMailMessage();
        userMail.setTo(correo);
        userMail.setSubject("Copia del mensaje");
        userMail.setText("Gracias por contactarnos. Aquí tienes una copia de tu mensaje:" + "\nAsunto"  + asunto + "\n" + mensaje);

        javaMailSender.send(userMail);
    }




}
