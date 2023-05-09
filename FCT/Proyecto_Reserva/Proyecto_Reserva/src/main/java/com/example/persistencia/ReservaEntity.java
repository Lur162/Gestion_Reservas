package com.example.persistencia;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class ReservaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_reserva;
	//ID_USUARIO
	@OneToOne
	private UsuariosEntity usuariosEntity;
	private LocalDate fecha_Entrada;
	
	private LocalDate fecha_Salida;
	
	private double importe;

	

	public ReservaEntity(UsuariosEntity usuariosEntity, LocalDate fecha_Entrada, LocalDate fecha_Salida,
			double importe) {
		super();
		this.usuariosEntity = usuariosEntity;
		this.fecha_Entrada = fecha_Entrada;
		this.fecha_Salida = fecha_Salida;
		this.importe = importe;
	}

	public long getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(long id_reserva) {
		this.id_reserva = id_reserva;
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

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	

}
