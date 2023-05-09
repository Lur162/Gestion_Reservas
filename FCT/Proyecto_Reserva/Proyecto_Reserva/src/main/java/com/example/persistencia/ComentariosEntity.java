package com.example.persistencia;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
 
public class ComentariosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_comentario;
	
	@Column(name = "id_reserva")
	@OneToOne
	private ReservaEntity reservaEntity;
	
	@Column(name = "id_usuarios")
	@OneToOne
	private UsuariosEntity usuariosEntity;
	
	@Column(name = "fecha_publicacion")
	
	private LocalDate fecha_publicacion;
	
	@Column(name = "comentario")
	private String comentario;

	public ComentariosEntity(ReservaEntity reservaEntity, UsuariosEntity usuariosEntity, LocalDate fecha_publicacion,
			String comentario) {
		super();
		this.reservaEntity = reservaEntity;
		this.usuariosEntity = usuariosEntity;
		this.fecha_publicacion = fecha_publicacion;
		this.comentario = comentario;
	}

	public ReservaEntity getReservaEntity() {
		return reservaEntity;
	}

	public void setReservaEntity(ReservaEntity reservaEntity) {
		this.reservaEntity = reservaEntity;
	}

	public UsuariosEntity getUsuariosEntity() {
		return usuariosEntity;
	}

	public void setUsuariosEntity(UsuariosEntity usuariosEntity) {
		this.usuariosEntity = usuariosEntity;
	}

	public LocalDate getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
