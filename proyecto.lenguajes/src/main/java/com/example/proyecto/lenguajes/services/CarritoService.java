package com.example.proyecto.lenguajes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.lenguajes.modelos.DetalleOrden;

@Service
public class CarritoService {
	
	private final DetalleOrdenService detalleOrdenService;
	private List<DetalleOrden> detalles = new ArrayList<>();

    @Autowired
    public CarritoService(DetalleOrdenService detalleOrdenService) {
        this.detalleOrdenService = detalleOrdenService;
    }

    public void agregarAlCarrito(DetalleOrden detalle) {
        int idProducto = detalle.getProducto().getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalle);
            detalleOrdenService.save(detalle); // Guardar en la base de datos
        }
    }
    
    
    

}
