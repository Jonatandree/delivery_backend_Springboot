package com.example.proyecto.lenguajes.services;

import java.util.List;

import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;

public interface CarritoServices {
	
	void agregarProductoAlCarrito(Usuario usuario, Producto producto, int cantidad);
    void eliminarProductoDelCarrito(Usuario usuario, Producto producto);
    List<DetalleOrden> obtenerCarrito(Usuario usuario);
    void vaciarCarrito(Usuario usuario);

}
