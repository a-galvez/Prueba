package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;

import lombok.Setter;

@Entity
@Table(name = "posiciones")
@Getter
@Setter

public class Posicion {

    @Id
    @Column(name ="codigoEquipo")
    private int codigoEquipo;

    private int empates;

    private int ganados;

    private int perdidos;

    @Column(name = "golesFavor")
    private int golesFavor;

    @Column(name = "golesContra")
    private int golesContra;

    private int puntos;

    @OneToOne
    @MapsId
    @JoinColumn(name = "codigoEquipo", referencedColumnName = "codigoEquipo", unique = true)
    @JsonIgnore
    private Equipo equipo;
  
    public Posicion(){

    }

    public Posicion(Equipo equipo) {
        this.codigoEquipo = equipo.getCodigoEquipo();
        this.empates = 0;
        this.ganados = 0;
        this.perdidos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.puntos = 0;
        this.equipo = equipo;
    }

   
}
