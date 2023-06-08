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


    public ComentarioDTO saveComentario(ComentarioDTO comentarioDto) {
        Comentario comentario = convertToEntity(comentarioDto);
        comentario = comentariosDao.save(comentario);
        return convertToDto(comentario);
    }
    public void saveComentarioo(Comentario comentario){
        comentariosDao.save(comentario);
    }

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





}
