package com.reservas.Reservas_BD.controller;


import com.reservas.Reservas_BD.DTO.ComentarioDTO;
import com.reservas.Reservas_BD.beans.Comentario;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;



    @GetMapping ("api/comentarioAll")
    public ResponseEntity<List<ComentarioDTO>> guardarComentario(){
        List<ComentarioDTO> comentarioDTOList=comentarioService.getAllComentarios();
        return new ResponseEntity<>(comentarioDTOList,HttpStatus.OK);

    }
    @PostMapping ("api/comentario")
    public ResponseEntity<?> guardarComentario(@RequestBody ComentarioDTO comentario){
       // Usuario usuario=comentarioService.getUser(comentario);
        //System.out.println(usuario.toString());
        Comentario comentario1=comentarioService.convertToEntity(comentario);

        if(comentarioService.isNullComent(comentario1, comentario1.getUsuario())==true){
            comentarioService.saveComentarioo(comentario1);
            return  ResponseEntity.ok(comentario1);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya tiene un comentario de la estancia");

    }
}
