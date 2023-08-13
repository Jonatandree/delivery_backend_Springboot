package com.example.proyecto.lenguajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.proyecto.lenguajes.modelos.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion,Integer> {
	
	
	

}
