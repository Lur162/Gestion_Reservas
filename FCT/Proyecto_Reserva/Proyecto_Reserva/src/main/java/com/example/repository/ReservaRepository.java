package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.persistencia.ReservaEntity;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{

}
