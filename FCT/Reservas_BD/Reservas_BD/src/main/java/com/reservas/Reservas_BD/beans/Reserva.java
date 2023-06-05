package com.reservas.Reservas_BD.beans;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reserva;
    //ID_USUARIO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne
    private Comentario comentario;


    private LocalDate fecha_Entrada;

    private LocalDate fecha_Salida;

    private double importeTotal;
    private int numero_personas;
    //private final double precioXNoche=50;


    //PARA LA BD

    public Reserva(Comentario comentario, Usuario usuario, LocalDate fecha_Entrada, LocalDate fecha_Salida, double importeTotal, int numero_personas) {
        this.comentario=comentario;
        this.usuario = usuario;
        this.fecha_Entrada = fecha_Entrada;
        this.fecha_Salida = fecha_Salida;
        this.importeTotal = importeTotal;
        this.numero_personas = numero_personas;

    }
    public Reserva() {
           }

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha_Entrada() {
        return fecha_Entrada;
    }

    public void setFecha_Entrada(LocalDate fecha_Entrada) {
        this.fecha_Entrada = fecha_Entrada;
    }

    public LocalDate getFecha_Salida() {
        return fecha_Salida;
    }

    public void setFecha_Salida(LocalDate fecha_Salida) {
        this.fecha_Salida = fecha_Salida;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public int getNumero_personas() {
        return numero_personas;
    }

    public void setNumero_personas(int numero_personas) {
        this.numero_personas = numero_personas;
    }
}
