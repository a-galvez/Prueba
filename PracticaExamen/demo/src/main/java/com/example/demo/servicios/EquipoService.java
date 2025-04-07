package com.example.demo.servicios;

import java.util.List;

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

    public String eliminarEquipo(int codigoEquipo){
        Equipo equipo = this.equipoRepository.findById(codigoEquipo);

        if(equipo != null && equipo.getPosicion() == null){
            this.equipoRepository.delete(equipo);
            return "El equipo ha sido borrado exitosamente";
        }
        return "El equipo no se puede eliminar";
    }

    public Equipo buscarEquipo(int codigoEquipo){
        Equipo equipo = this.equipoRepository.findById(codigoEquipo);

        if(equipo != null){
            return equipo;
        }
        return null;
    }

    public String simularPartido(){
        List<Equipo> equipos = (List<Equipo>) this.equipoRepository.findAll();

        if(equipos.size() >= 6){

            for(int i = 0; i < equipos.size(); i++){
                for(int j = 0; j < equipos.size(); j++){
                    if(i != j) {
                        for(int k = 0; k < 2; k++){
                            Equipo equipoA = equipos.get(i);
                            Equipo equipoB = equipos.get(j);

                            int cantGolesA = (int) (Math.random() * 7); 
                            int cantGolesB = (int) (Math.random() * 7);

                            equipoA.getPosicion().setGolesFavor(equipoA.getPosicion().getGolesFavor() + cantGolesA);
                            equipoB.getPosicion().setGolesFavor(equipoB.getPosicion().getGolesFavor() + cantGolesB);

                            equipoA.getPosicion().setGolesContra(equipoA.getPosicion().getGolesContra() + cantGolesB);
                            equipoB.getPosicion().setGolesContra(equipoB.getPosicion().getGolesContra() + cantGolesA);

                            if(cantGolesA < cantGolesB){
                                //Gana equipo B -- suma 3pts al equipo B
                                equipoA.getPosicion().setPerdidos(equipoA.getPosicion().getPerdidos() + 1);
                                equipoA.getPosicion().setPuntos(equipoA.getPosicion().getPuntos() + 0);

                                equipoB.getPosicion().setGanados(equipoB.getPosicion().getGanados() + 1);
                                equipoB.getPosicion().setPuntos(equipoB.getPosicion().getPuntos() + 3);
                            }
                            
                            if(cantGolesA > cantGolesB){
                                //Gana equipo A -- suma 3pts al equipo A
                                equipoA.getPosicion().setGanados(equipoA.getPosicion().getGanados() + 1);
                                equipoA.getPosicion().setPuntos(equipoA.getPosicion().getPuntos() + 3);

                                equipoB.getPosicion().setPerdidos(equipoB.getPosicion().getPerdidos() + 1);
                                equipoB.getPosicion().setPuntos(equipoB.getPosicion().getPuntos() + 0);
                            }
            
                            if(cantGolesA == cantGolesB){
                                //Empate -- suma 1pt cada uno
                                equipoA.getPosicion().setEmpates(equipoA.getPosicion().getEmpates() + 1);
                                equipoA.getPosicion().setPuntos(equipoA.getPosicion().getPuntos() + 1);

                                equipoB.getPosicion().setEmpates(equipoB.getPosicion().getEmpates() + 1);
                                equipoB.getPosicion().setPuntos(equipoB.getPosicion().getPuntos() + 1);
                            }

                            this.equipoRepository.save(equipoA);
                            this.equipoRepository.save(equipoB);
                        }

                    }

                }

            }

        }

        return "Se necesitan mínimo 6 equipos para que se enfrenten entre si";
    }
    
    public List<Posicion> obtenerPosiciones(){
        List<Posicion> posiciones = (List<Posicion>) this.posicionRepository.findAll();

        if(posiciones != null)
        return posiciones;

        return null;
    }
}
