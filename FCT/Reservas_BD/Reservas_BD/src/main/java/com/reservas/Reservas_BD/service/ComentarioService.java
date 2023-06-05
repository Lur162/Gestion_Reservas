package com.reservas.Reservas_BD.service;

import com.reservas.Reservas_BD.DTO.ComentarioDTO;
import com.reservas.Reservas_BD.beans.Comentario;
import com.reservas.Reservas_BD.beans.Reserva;
import com.reservas.Reservas_BD.beans.Usuario;
import com.reservas.Reservas_BD.persistencia.DAO.ComentarioDAO;
import com.reservas.Reservas_BD.persistencia.DAO.ReservaDAO;
import com.reservas.Reservas_BD.persistencia.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioDAO comentariosDao;
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<ComentarioDTO> getAllComentarios() {
        List<Comentario> comentarios = (List<Comentario>) comentariosDao.findAll();
        return comentarios.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public ComentarioDTO getComentarioByUser(Usuario usuario){
        Comentario comentario=comentariosDao.findByUsuario(usuario);
        return convertToDto(comentario);


    }

    public ComentarioDTO getComentarioById(Long id) {
        Comentario comentario = comentariosDao.findById(id).orElse(null);
        return (comentario != null) ? convertToDto(comentario) : null;
    }

    public ComentarioDTO saveComentario(ComentarioDTO comentarioDto) {
        Comentario comentario = convertToEntity(comentarioDto);
        comentario = comentariosDao.save(comentario);
        return convertToDto(comentario);
    }
    public void saveComentarioo(Comentario comentario){
        comentariosDao.save(comentario);
    }
    /*
    public boolean saveComentarioSinDTO(Comentario comentarioDto, Usuario usuario) {

      if(comentariosDao.findByUsuario(usuario)==null){
          comentariosDao.save(comentarioDto);
          return true;
      }
        return false;
    }
    */
    public boolean isNullComent(Comentario comentario, Usuario usuario){
        if(comentariosDao.findByUsuario(usuario)==null){
            return true;
        }else {
            return false;
        }
    }
        public Usuario getUser(ComentarioDTO comentarioDTO){
        List<Usuario> usuarioList= (List<Usuario>) usuarioDAO.findAll();
            for (Usuario usuarios: usuarioList
                 ) {
                if( comentarioDTO.getId_usuario()==usuarios.getId_usuario()){
                    return usuarios;
                }
            }
            return null;
        }

    public ComentarioDTO updateComentario(Long id, ComentarioDTO comentarioDto) {
        Comentario comentario = convertToEntity(comentarioDto);
        comentario.setId_comentario(id);
        comentario = comentariosDao.save(comentario);
        return convertToDto(comentario);
    }

    public void deleteComentario(Long id) {
        comentariosDao.deleteById(id);
    }

    public ComentarioDTO convertToDto(Comentario comentario) {
        return new ComentarioDTO( comentario.getUsuario().getId_usuario(), comentario.getFecha_publicacion(),comentario.getTexto(),comentario.getUsuario().getNombre());
    }

    public Comentario convertToEntity(ComentarioDTO comentarioDto) {
        Usuario usuario = getUser(comentarioDto);
        //Long id, Usuario usuario, LocalDate fecha_publicacion, String texto
        return new Comentario(usuario,comentarioDto.getFecha_publicacion(),comentarioDto.getTexto());
    }
    //HAY QUE CONTROLAR QUE EL USUARIO TENGA RESERVA Y SEA POSTERIOR A LA FECHA SALIDA PARA CREAR EL COMENTARIO

    public boolean tieneRerva(Usuario usuario, Reserva reserva, Comentario comentario){
        // List<Usuario> usuarioList=usuarioDAO.findAll();
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();
        Usuario usuario1=comentario.getUsuario();
        for (Reserva reserva1:reservaList
        ) {
            if(usuario1.getId_usuario()==reserva1.getUsuario().getId_usuario()){
                //Entonces tiene reserva
                return true;
            }
        }
        return false;
    }

    public boolean createComentario(ComentarioDTO comentarioDTO, Usuario usuario){

        //SI EL USUARIO ESTÁ EN LA LISTA DE RESERVAS, HACE COMENTARIO
        //FALTARIA CONTROLAR LAS FECHAS SALIDA
        List<Reserva> reservaList= (List<Reserva>) reservaDAO.findAll();
        Reserva reservita=null;
        Comentario comentario=convertToEntity(comentarioDTO);
        for (Reserva reserva: reservaList
        ) {
            if(reserva.getUsuario().getId_usuario()==usuario.getId_usuario() && reserva.getFecha_Salida().isAfter(LocalDate.now())){

                saveComentario(comentarioDTO);
                reservita.setComentario(comentario);
                reservaDAO.save(reservita);
                return true;
            }
        }
        return false;
    }
}
