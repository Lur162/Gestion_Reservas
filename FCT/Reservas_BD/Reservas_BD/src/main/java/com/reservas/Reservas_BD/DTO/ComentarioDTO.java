package com.reservas.Reservas_BD.DTO;

import java.time.LocalDate;

public class ComentarioDTO {


    private Long id_usuario;
    private LocalDate fecha_publicacion;
    private String texto;
    private String nombre_usuario;

    public ComentarioDTO() {
    }



    public ComentarioDTO(Long id_usuario, LocalDate fecha_publicacion, String texto, String nombre_usuario) {

        this.id_usuario = id_usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.texto = texto;
        this.nombre_usuario = nombre_usuario;
    }



    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
