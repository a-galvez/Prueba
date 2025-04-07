package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Equipo;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer>{
    
    public Equipo findById(int codigoEquipo);
}
