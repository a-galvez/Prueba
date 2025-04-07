package com.example.demo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posiciones")
@Getter
@Setter
public class Posicion {

    private int codigoEquipo;

    private int empates;

    private int ganados;

    private int perdidos;

    private int golesFavor;

    private int golesContra;

    private int puntos;

    @OneToOne
    @JoinColumn(name = "codigoEquipo", referencedColumnName = "codigoEquipo")
    private Equipo equipo;

    public Posicion(){
        
    }

    public Posicion(int codigoEquipo, int empates, int ganados, int perdidos, int golesFavor, int golesContra,
            int puntos, Equipo equipo) {
        this.codigoEquipo = codigoEquipo;
        this.empates = empates;
        this.ganados = ganados;
        this.perdidos = perdidos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.puntos = puntos;
        this.equipo = equipo;
    }
}
