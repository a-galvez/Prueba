package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositorios.EquipoRepository;
import com.example.demo.repositorios.PosicionRepository;
import com.example.demo.servicios.EquipoService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    
    @Autowired
    private EquipoService EquipoService;

    private EquipoRepository equipoRepository;

    private PosicionRepository posicionRepository;

    public EquipoController(EquipoRepository equipoRepository, PosicionRepository posicionRepository) {
        this.equipoRepository = equipoRepository;
        this.posicionRepository = posicionRepository;
    }

    
}
