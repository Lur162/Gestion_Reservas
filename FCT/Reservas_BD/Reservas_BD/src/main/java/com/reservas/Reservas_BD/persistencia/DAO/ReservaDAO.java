package com.reservas.Reservas_BD.persistencia.DAO;

import com.reservas.Reservas_BD.beans.Reserva;
import com.reservas.Reservas_BD.beans.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaDAO extends CrudRepository<Reserva, Long> {
    /*
    @Query("@Query(“select * from reserva where fecha_Entrada>= fechaInicio AND fecha_salida<=fechaFin”);")
    List<Reserva> findByFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
*/

    List<Reserva> findByUsuario(Usuario usuario);


}
