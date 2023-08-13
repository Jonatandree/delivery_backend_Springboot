package com.example.proyecto.lenguajes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Repartidor;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer>{
    
     List<Orden> findOrdenesById(Integer id);
}
