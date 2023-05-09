package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.persistencia.ComentariosEntity;
import com.example.persistencia.ReservaEntity;

public interface ComentarioRepository extends JpaRepository<ComentariosEntity, Long>{

}
