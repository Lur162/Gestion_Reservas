package com.reservas.Reservas_BD.DTO;

import java.time.LocalDate;

public class ReservaDTO {

    private long usuario_id;


    private LocalDate fecha_Entrada;

    private LocalDate fecha_Salida;

    private double importeTotal;
    private int numero_personas;
    private final double precioXNoche=50;

    public ReservaDTO(long usuario_id, LocalDate fecha_Entrada, LocalDate fecha_Salida, double importeTotal, int numero_personas) {
        this.usuario_id = usuario_id;
        this.fecha_Entrada = fecha_Entrada;
        this.fecha_Salida = fecha_Salida;
        this.importeTotal = importeTotal;
        this.numero_personas = numero_personas;
    }



    public long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(long usuario_id) {
        this.usuario_id = usuario_id;
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

    public double getPrecioXNoche() {
        return precioXNoche;
    }
}
