package com.example.proyecto.lenguajes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Usuario;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{
    
    List<Orden> findByUsuario (Usuario usuario);
}
