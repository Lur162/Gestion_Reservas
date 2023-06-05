package com.reservas.Reservas_BD.persistencia.DAO;

import com.reservas.Reservas_BD.beans.Comentario;
import com.reservas.Reservas_BD.beans.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioDAO extends CrudRepository<Comentario, Long> {
    Comentario findByUsuario(Usuario usuario);

}
