package com.reservas.Reservas_BD.controller;


import com.reservas.Reservas_BD.DTO.UsuarioDTO;
import com.reservas.Reservas_BD.DTO.UsuarioLoginReturnDTO;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @PostMapping("api/user/create")
    public ResponseEntity<?> registrarUSer(@RequestBody Usuario user){

        //GUARDAR DATOS String nombre, String apellido, String correo, String contrasenia
        Usuario usuario=new Usuario(user.getNombre(),user.getApellido(),user.getCorreo(),user.getContrasenia());
        if(usuarioService.isSave(usuario)==true)  {
            return new ResponseEntity<Usuario>(user, HttpStatus.OK);

        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginRequest) {
        String correo = loginRequest.getCorreo();
        String contrasenia = loginRequest.getContrasenia();
        Usuario userLoged= usuarioService.loginUser(correo, contrasenia);

        if (userLoged != null) {
            UsuarioLoginReturnDTO userReturn = new UsuarioLoginReturnDTO(userLoged.getCorreo(), "TOKEN!!!",userLoged.getId_usuario());


            return new ResponseEntity<>(userReturn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("usuario invalido", HttpStatus.NOT_FOUND);
        }
    }

}
