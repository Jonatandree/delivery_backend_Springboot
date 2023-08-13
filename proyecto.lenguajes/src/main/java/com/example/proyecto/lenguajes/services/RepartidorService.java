package com.example.proyecto.lenguajes.services;

import java.util.List;


import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Repartidor;

public interface RepartidorService {
    

    public Repartidor crearRepartidor(Repartidor repartidor);
    public Repartidor obtenerRepartidorPorId(Integer id);
    public List<Repartidor> obtenerTodosLosRepartidores();
    public void eliminarRepartidor(Integer id);
     public Repartidor asignarOrden(Integer id, Orden orden);
     List<Orden> findOrdenesById(Integer id);
     public Repartidor asignarOrdenes(Integer repartidorId, Integer ordenId);
    
}
