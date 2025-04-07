package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Posicion;

@Repository
public interface PosicionRepository extends CrudRepository<Posicion, Integer>{
    
}
