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


    public List<Reserva> getReservaPorUsuario(Usuario usuario){
        List<Reserva> reservaList= reservaDAO.findByUsuario(usuario);
        return reservaList;
    }



    public Reserva isReservaByUser( long id_user){
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();

        //HAY QUE CONTROLAR QUE OTRO USUARIO NO BORRE LA RESERVA DE LOS DEMAS
        for (Reserva reserva: reservaList
        ) {
            if(reserva.getUsuario().getId_usuario()==id_user){
                return reserva;
            }
        }
        return null;

    }
    public boolean deleteReservaID(Long id_reserva, long id_user) {
        Reserva reserva=isReservaByUser(id_reserva);
        boolean isDelete=false;
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();
        for (Reserva reservaa:reservaList
        ) {
            if(reservaa.getId_reserva()==id_reserva && reservaa.getUsuario().getId_usuario()==id_user ){
                isDelete=true;
                reservaDAO.deleteById(id_reserva);
            }
        }
        return isDelete;
    }

    public ReservaDTO createReserva(ReservaDTO reservaDto) {
        Reserva reserva = convertToEntity(reservaDto);
        LocalDate nuevaFechaEntrada = reservaDto.getFecha_Entrada().plusDays(1);
        LocalDate nuevaFechaSalida = reservaDto.getFecha_Salida().plusDays(1);
            reserva.setFecha_Entrada(nuevaFechaEntrada);
            reserva.setFecha_Salida(nuevaFechaSalida);
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
