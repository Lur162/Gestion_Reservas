package com.reservas.Reservas_BD.service;


import com.reservas.Reservas_BD.DTO.ReservaDTO;
import com.reservas.Reservas_BD.beans.Reserva;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.persistencia.DAO.ReservaDAO;
import com.reservas.Reservas_BD.persistencia.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservasService {
    @Autowired
    private ReservaDAO reservaDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<ReservaDTO> getAllReservas() {
        List<Reserva> reservas = (List<Reserva>) reservaDAO.findAll();
        return reservas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ReservaDTO getReservaById(Long id) {
        Reserva reserva = reservaDAO.findById(id).orElse(null);
        return (reserva != null) ? convertToDto(reserva) : null;
    }
    public List<Reserva> getReservaPorUsuario(Usuario usuario){
        List<Reserva> reservaList= reservaDAO.findByUsuario(usuario);
        return reservaList;
    }

    //TENGO QUE CONTROLAR QUE OTRO USUARIO NO BORRE LA RESERVA DE LOS DEMAS


    public Reserva isReservaByUser( long id_user){
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();

        for (Reserva reserva: reservaList
             ) {
            if(reserva.getUsuario().getId_usuario()==id_user){
                return reserva;
            }
        }
        return null;

    }

    public boolean deleteReserva(Long id_user) {
        Reserva reserva=isReservaByUser(id_user);
        boolean isDelete=false;
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();
        for (Reserva reservaa:reservaList
        ) {
            if(reservaa==reserva){
                isDelete=true;
                reservaDAO.deleteById(reservaa.getId_reserva());
            }
        }
        return isDelete;
    }

    public ReservaDTO createReserva(ReservaDTO reservaDto) {
        Reserva reserva = convertToEntity(reservaDto);
        if(isDisponible(reservaDto.getFecha_Entrada(),reservaDto.getFecha_Salida())==true){
            reserva = reservaDAO.save(reserva);
            return convertToDto(reserva);
        }

        return null;

    }
    public long findID(Reserva reserva){
       List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();
        for (Reserva reservas: reservaList
             ) {
            if(reservas.getUsuario()==reserva.getUsuario() && reservas.getFecha_Entrada()==reserva.getFecha_Entrada()){
                return reservas.getId_reserva();
            }
        }
        return 0;
    }

    //DISPONIBILIDAD POR FECHA
    public boolean isDisponible(LocalDate fecha_inicio, LocalDate fecha_salida){
        List<Reserva> reservas= (List<Reserva>) reservaDAO.findAll();
        for (Reserva reserva:reservas
        ) {
            if(reserva.getFecha_Entrada()==fecha_inicio || reserva.getFecha_Salida()==fecha_salida){
                return false;
            }

        }
        return true;
    }
    /*
    public boolean isFechaDisponible(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = reservaDAO.findByFecha_EntradaBetweenOrFecha_SalidaBetween(fechaInicio, fechaFin, fechaInicio, fechaFin);
        return reservas.isEmpty();
    }
*/
    public ReservaDTO updateReserva(Long id, ReservaDTO reservaDto) {
        Reserva reserva = convertToEntity(reservaDto);
        reserva.setId_reserva(id);
        reserva = reservaDAO.save(reserva);
        return convertToDto(reserva);
    }



    public ReservaDTO convertToDto(Reserva reserva) {
        return new ReservaDTO(reserva.getUsuario().getId_usuario(), reserva.getFecha_Entrada(), reserva.getFecha_Salida(),reserva.getImporteTotal(),reserva.getNumero_personas());
    }

    public Reserva convertToEntity(ReservaDTO reservaDto) {
        Usuario usuario=usuarioDAO.findById(reservaDto.getUsuario_id()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        //  Comentario comentario = new Comentario(reservaDto.getComentario_id());
        return new Reserva(null, usuario, reservaDto.getFecha_Entrada(), reservaDto.getFecha_Salida(),reservaDto.getImporteTotal(),reservaDto.getNumero_personas());
    }
    public Usuario findUserByReserva(ReservaDTO reservaDTO){
        List<Usuario> usuarioList= (List<Usuario>) usuarioDAO.findAll();
        for (Usuario usuario:usuarioList
             ) {
            if(usuario.getId_usuario()== reservaDTO.getUsuario_id()){
                return usuario;
            }
        }
        return null;
    }
    public List<ReservaDTO> convertToDtoList(List<Reserva> reservas){
        List<ReservaDTO> reservaDTOList=new ArrayList<>();
        for (Reserva reserva:reservas
        ) {
            reservaDTOList.add(convertToDto(reserva));
        }
        return reservaDTOList;
    }

    public List<Reserva> obtenerReservasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = (List<Reserva>) reservaDAO.findAll(); // Método para obtener todas las reservas

        List<Reserva> reservasEnIntervalo = new ArrayList<>();

        for (Reserva reserva : reservas) {
            LocalDate fechaEntrada = reserva.getFecha_Entrada();
            LocalDate fechaSalida = reserva.getFecha_Salida();

            // Verificar si hay intersección entre el intervalo dado y las fechas de reserva
            if ((fechaInicio.isBefore(fechaSalida) || fechaInicio.isEqual(fechaSalida))
                    && (fechaFin.isAfter(fechaEntrada) || fechaFin.isEqual(fechaEntrada))) {
                reservasEnIntervalo.add(reserva);
            }
        }

        return reservasEnIntervalo;
    }
}
