package com.reservas.Reservas_BD.service;

import com.reservas.Reservas_BD.DTO.ReservaDTO;
import com.reservas.Reservas_BD.DTO.UsuarioDTO;
import com.reservas.Reservas_BD.beans.Reserva;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.persistencia.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    //PARA EL REGISTRO, si existe , ERROR DE REGISTRO, SI NO SE REGISTRA
    public boolean isExist(String correo){
        boolean isExist=false;
        List<Usuario> usuarioList= (List<Usuario>) usuarioDAO.findAll();
        for (Usuario usuario: usuarioList
             ) {
            if(usuario.getCorreo().equalsIgnoreCase(correo)){
                isExist= true;
            }
        }
        return isExist;
    }


    //LOGIN
    public Optional<Usuario> UserValid(UsuarioDTO usuarioDTO){
        List<Usuario> usuarioList= (List<Usuario>) usuarioDAO.findAll();

            Usuario  usuario=converToEntity(usuarioDTO);

            for (Usuario usuario1: usuarioList
                 ) {
                if(usuario1.getCorreo().equalsIgnoreCase(usuario.getCorreo()) && usuario1.getContrasenia().equalsIgnoreCase(usuario.getContrasenia())){
                   return Optional.of(usuario1);
                }
            }

        return null;
    }

    //LOGIN

    public boolean UserValido(Usuario usuario) {
        Usuario usuario1 = usuarioDAO.findByCorreo(usuario.getCorreo());
        if (usuario1 != null && usuario1.getContrasenia().equals(usuario.getContrasenia())) {
            return true;
        }
        return false;
    }


    public boolean isSave(Usuario usuario){
        if(isExist(usuario.getCorreo())==false){
            usuarioDAO.save(usuario);
            return true;
        }
        return false;
    }
    public Usuario converToEntity(UsuarioDTO usuarioDTO){

        List<Usuario> usuarioList= new ArrayList<>();
        usuarioList= (List<Usuario>) usuarioDAO.findAll();

        for (Usuario usuario:usuarioList
             ) {
            if(usuario.getCorreo().equalsIgnoreCase(usuarioDTO.getCorreo())){
                return usuario;
            }
        }
        return null;

    }
    public Usuario loginUser(String correo, String contrasenia) {
        Usuario usuario = usuarioDAO.findByCorreo(correo);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

}
