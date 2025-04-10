package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
    
}
