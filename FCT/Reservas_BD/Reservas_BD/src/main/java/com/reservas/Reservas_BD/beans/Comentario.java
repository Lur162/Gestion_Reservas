package com.reservas.Reservas_BD.beans;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_comentario;


    @OneToOne
    private Usuario usuario;

    @Column(name = "fecha_publicacion")
    private LocalDate fecha_publicacion;

    @Column(name = "texto")
    private String texto;

    public Comentario(Usuario usuario, LocalDate fecha_publicacion, String texto) {
        this.usuario = usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.texto = texto;
    }
    public Comentario() {

    }


    public long getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(long id_comentario) {
        this.id_comentario = id_comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
