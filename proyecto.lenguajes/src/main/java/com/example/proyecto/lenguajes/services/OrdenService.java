package com.example.proyecto.lenguajes.services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Usuario;

public interface OrdenService {
    List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
	Orden obtenerOrdenPorId(Integer id);
}

