package com.reservas.Reservas_BD.persistencia.DAO;

import com.reservas.Reservas_BD.beans.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);


}
