package com.reservas.Reservas_BD.controller;


import com.reservas.Reservas_BD.DTO.ReservaDTO;
import com.reservas.Reservas_BD.beans.Reserva;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.service.ReservasService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import java.time.Clock;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReservaController {


    @Autowired
    private ReservasService reservasService;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/timezone")
    public String getTimeZone() {
        ZoneId zoneId = ZoneId.systemDefault();
        return "Zona horaria actual: " + zoneId;
    }

    @DeleteMapping("api/reserva/deleteById/{id}/{id_user}")

    public ResponseEntity<?> eliminarReserva(@PathVariable Integer id, @PathVariable Integer id_user){
        Long ides=Long.parseLong(String.valueOf(id));
        Long id_users=Long.parseLong(String.valueOf(id_user));
       if(reservasService.deleteReserva(id_users)==true){

           return ResponseEntity.ok().body("{\"success\": true}");
       }
           return ResponseEntity.badRequest().body("{\"success\": false}");
    }
  /*
    @DeleteMapping("api/reserva/deleteById")

    public ResponseEntity<?> eliminarReserva(@RequestBody ReservaDTO reservaDTO){
        Reserva reserva=reservasService.convertToEntity(reservaDTO);

        Long id=reserva.getId_reserva();
        if(reservasService.deleteReserva(id)){
            return ResponseEntity.ok(reserva);
        }else {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }
*/
    @GetMapping("api/reserva/{usuario}")
    public ResponseEntity<List<ReservaDTO>> getReserva(@RequestBody Usuario usuario){

        List<Reserva> reservaList=reservasService.getReservaPorUsuario(usuario);
        List<ReservaDTO> reservaDTOList=reservasService.convertToDtoList(reservaList);
        if(reservaDTOList.isEmpty()){
            return new ResponseEntity<>(reservaDTOList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservaDTOList, HttpStatus.OK);


    }





    @PostMapping("api/reserva/reservas")
    public ResponseEntity<?> confirmacionReserva(@RequestBody ReservaDTO reserva) throws MessagingException {
        // Lógica para registrar la reserva

            Usuario usuario=reservasService.findUserByReserva(reserva);
            Reserva reserva1=reservasService.convertToEntity(reserva);
        List<Reserva> reservasenFecha=reservasService.obtenerReservasEntreFechas(reserva1.getFecha_Entrada(), reserva1.getFecha_Salida());
        if(!reservasenFecha.isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las fechas seleccionadas no están disponibles.");
        }
        //GUARDAR LA RESERVA
        reservasService.createReserva(reserva);
        Long id= reservasService.findID(reserva1);
        System.out.println( reservasService.findID(reserva1));
        //MANDAR MAIL DE CONFIRMACION DE RESERVA
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        message.setFrom(new InternetAddress("casarurallosmaragatos@gmail.com"));
        helper.setTo(usuario.getCorreo());
        helper.setSubject("Confirmación de reserva");
        String content ="<html><body><h1>Confirmación de reserva</h1><p>Datos de la reserva:</p><ul><li>Nombre: " + usuario.getNombre() +
                "</li><li>Fecha Entrada: " + reserva1.getFecha_Entrada()  +
                "</li><li>Fecha Salida: " + reserva1.getFecha_Salida()+
                "</li><li>Importe: " + reserva1.getImporteTotal()+
                "</li><li>Código de Identificación de la reserva: " + id+
                "</li></ul>"+"El importe se abonará en el establecimiento"+
                "</li></ul></body></html>";
        helper.setText(content, true);
        mailSender.send(message);




        return ResponseEntity.ok(reserva);
    }


}
