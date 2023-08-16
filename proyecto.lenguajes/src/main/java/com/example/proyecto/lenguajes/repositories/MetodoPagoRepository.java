package com.example.proyecto.lenguajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.lenguajes.modelos.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Integer> {
	
	
	

}
