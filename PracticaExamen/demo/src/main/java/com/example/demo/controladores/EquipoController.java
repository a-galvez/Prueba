package com.example.demo.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entidades.Equipo;
import com.example.demo.entidades.Posicion;
import com.example.demo.servicios.EquipoService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    
    @Autowired
    private EquipoService equipoService;

   
    @PostMapping("/crear")
    public String crearEquipo(@RequestBody Equipo equipo){
        return this.equipoService.crearEquipo(equipo);
    }
 
    @DeleteMapping("/eliminar/{codigoEquipo}")
    public String eliminarEquipo(@PathVariable(name ="codigoEquipo") int codigoEquipo){
        return this.equipoService.eliminarEquipo(codigoEquipo);
    }

    @GetMapping("/buscar/{codigoEquipo}")
    public Equipo buscarEquipo(@PathVariable(name="codigoEquipo") int codigoEquipo ){

        return this.equipoService.buscarEquipo(codigoEquipo);
    }

    @PostMapping("/simular-partidos")
    public String simularPartidos() {
        equipoService.simularPartidos();
        return "¡Partidos simulados exitosamente!";
    }

    @GetMapping("/tabla")
    public List<Posicion> obtenerPosiciones(){
        return this.equipoService.obtenerPosiciones();
    }
}
