package com.example.demo.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipos")
@Getter
@Setter
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoEquipo")
    private int codigoEquipo;

    private String nombre;

    private double ataque;

    private double defensa;

    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Posicion posicion;

    public Equipo(){

    }

    public Equipo(int codigoEquipo, String nombre, double ataque, double defensa, Posicion posicion) {
        this.codigoEquipo = codigoEquipo;
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.posicion = posicion;
    }
}
