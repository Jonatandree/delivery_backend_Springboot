package com.example.proyecto.lenguajes.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.repositories.OrdenRepository;
import com.example.proyecto.lenguajes.services.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public String generarNumeroOrden() {
    	// Generar un número único basado en la fecha y algún valor aleatorio
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fechaActual = dateFormat.format(new Date());
        int numeroAleatorio = (int) (Math.random() * 10000); // Número aleatorio entre 0 y 9999
        return "ORD" + fechaActual + "-" + numeroAleatorio;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

	@Override
	public Orden obtenerOrdenPorId(Integer id) {
		return ordenRepository.findById(id).orElse(null);
	}
    
}
