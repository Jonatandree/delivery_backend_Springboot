package com.example.proyecto.lenguajes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.services.impl.OrdenServiceImpl;



@RestController
@RequestMapping("/orden")
public class OrdenController {
	
	@Autowired
    private OrdenServiceImpl ordenesserviceimpl;
	
	
	@GetMapping("/listar")
    public List<Orden> obtenerOrdenes() {
        return ordenesserviceimpl.findAll();
    }
	
	@PostMapping("/guardar")
    public Orden guardarOrden(@RequestBody Orden orden) {
        return ordenesserviceimpl.save(orden);
    }
	
	
	
	@GetMapping("/obtener/{id}")
	public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Integer id) {
	    Optional<Orden> optionalOrden = ordenesserviceimpl.findById(id);

	    if (optionalOrden.isPresent()) {
	        Orden orden = optionalOrden.get();
	        return ResponseEntity.ok(orden);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
