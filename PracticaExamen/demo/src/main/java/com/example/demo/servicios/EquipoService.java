package com.example.demo.servicios;

import java.util.List;
import java.util.Random;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Equipo;
import com.example.demo.entidades.Posicion;
import com.example.demo.repositorios.EquipoRepository;
import com.example.demo.repositorios.PosicionRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PosicionRepository posicionRepository;

    private Random random = new Random();

    public String crearEquipo(Equipo equipo){
        Equipo nvoEquipo = new Equipo();
        nvoEquipo.setNombre(equipo.getNombre());
        nvoEquipo.setAtaque(equipo.getAtaque());
        nvoEquipo.setDefensa(equipo.getDefensa());

        if(equipo.getPosicion() != null){
            Posicion posicion = new Posicion();
            posicion.setCodigoEquipo(nvoEquipo.getCodigoEquipo());
            posicion.setEmpates(nvoEquipo.getPosicion().getEmpates());
            posicion.setGanados(nvoEquipo.getPosicion().getPerdidos());
            posicion.setPerdidos(nvoEquipo.getPosicion().getPerdidos());
            posicion.setGolesFavor(nvoEquipo.getPosicion().getGolesFavor());
            posicion.setGolesContra(nvoEquipo.getPosicion().getGolesContra());
            posicion.setPuntos(nvoEquipo.getPosicion().getPuntos());
            posicionRepository.save(posicion);
        }

        equipoRepository.save(nvoEquipo);

        return "Equipo creado exitosamente"; 
    }

    public String eliminarEquipo(int codigoEquipo) {

        Optional<Equipo> optionalEquipo = equipoRepository.findById(codigoEquipo);
    
        if (optionalEquipo.isPresent()) {
            Equipo equipo = optionalEquipo.get();
    
            if (equipo.getPosicion() == null) {
                equipoRepository.delete(equipo);
                return "El equipo ha sido borrado exitosamente";
            } else {
                return "El equipo no se puede eliminar porque tiene una posición asignada";
            }
        }
    
        return "No se encontró un equipo con ese código";
    }
    

    public Equipo buscarEquipo(int codigoEquipo) {
        Optional<Equipo> equipo = this.equipoRepository.findById(codigoEquipo);
        if (equipo.isPresent()) {
            return equipo.get();
        }
        return null;
    }
    

    public void simularPartidos() {
        List<Equipo> equipos = (List<Equipo>) equipoRepository.findAll();
    
        // Crear los equipos faltantes
        if (equipos.size() < 6) {
            for (int i = equipos.size() + 1; i <= 6; i++) {
                Equipo equipo = new Equipo();
                equipo.setNombre("Equipo " + i);
                equipo.setAtaque(random.nextDouble() * 100);
                equipo.setDefensa(random.nextDouble() * 100);
                equipoRepository.save(equipo);
            }
            equipos = (List<Equipo>) equipoRepository.findAll(); // actualizar lista
        }
    
        // Asegurar que todos los equipos tengan una posición inicial
        for (Equipo equipo : equipos) {
            if (!posicionRepository.existsById(equipo.getCodigoEquipo())) {
                Posicion posicion = new Posicion();
                posicion.setEquipo(equipo);
                posicion.setEmpates(0);
                posicion.setGanados(0);
                posicion.setPerdidos(0);
                posicion.setGolesFavor(0);
                posicion.setGolesContra(0);
                posicion.setPuntos(0);
                posicionRepository.save(posicion);
            }
        }
    
        // Simular partidos ida y vuelta
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                Equipo equipoA = equipos.get(i);
                Equipo equipoB = equipos.get(j);
    
                // Partido ida
                int golesA = random.nextInt(6);
                int golesB = random.nextInt(6);
                
                actualizarPosiciones(equipoA, equipoB, golesA, golesB );
    
                // Partido vuelta
                golesA = random.nextInt(6);
                golesB = random.nextInt(6);
                actualizarPosiciones(equipoA, equipoB,golesA, golesB  );
            }
        }
    }
    


    public void actualizarPosiciones(Equipo equipoA, Equipo equipoB, int golesA, int golesB) {
        // Buscar o crear posición del equipo A
        Posicion posicionA = posicionRepository.findById(equipoA.getCodigoEquipo())
                .orElse(new Posicion(equipoA));
    
        // Buscar o crear posición del equipo B
        Posicion posicionB = posicionRepository.findById(equipoB.getCodigoEquipo())
                .orElse(new Posicion(equipoB));
    
        // Sumar goles a favor y en contra
        posicionA.setGolesFavor(posicionA.getGolesFavor() + golesA);
        posicionA.setGolesContra(posicionA.getGolesContra() + golesB);
    
        posicionB.setGolesFavor(posicionB.getGolesFavor() + golesB);
        posicionB.setGolesContra(posicionB.getGolesContra() + golesA);
    
        // Calcular resultado
        if (golesA > golesB) {
            posicionA.setGanados(posicionA.getGanados() + 1);
            posicionA.setPuntos(posicionA.getPuntos() + 3);
            posicionB.setPerdidos(posicionB.getPerdidos() + 1);
        } else if (golesA < golesB) {
            posicionB.setGanados(posicionB.getGanados() + 1);
            posicionB.setPuntos(posicionB.getPuntos() + 3);
            posicionA.setPerdidos(posicionA.getPerdidos() + 1);
        } else {
            posicionA.setEmpates(posicionA.getEmpates() + 1);
            posicionB.setEmpates(posicionB.getEmpates() + 1);
            posicionA.setPuntos(posicionA.getPuntos() + 1);
            posicionB.setPuntos(posicionB.getPuntos() + 1);
        }
    
        // Guardar actualizaciones
        posicionRepository.save(posicionA);
        posicionRepository.save(posicionB);
    }
    

    public List<Posicion> obtenerPosiciones(){
        List<Posicion> posiciones = (List<Posicion>) this.posicionRepository.findAll();

        if(posiciones != null){

            return posiciones;
        }

        return null;
    }
}
