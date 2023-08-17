package com.example.proyecto.lenguajes.services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;


public interface DetalleOrdenService {
    
    	DetalleOrden save (DetalleOrden detalleOrden);
    	public Optional<DetalleOrden> get(Integer id);
    	public void delete(Integer id);
    	public void eliminar(DetalleOrden detalleOrden);
    	public void eliminarProductoCarrito(List<DetalleOrden> ordenesNueva);
    	public void eliminarProductoDelCarrito(Integer idProducto);
    	void guardarDetalleOrden(DetalleOrden detalleOrden);
        void eliminarDetalleOrden(DetalleOrden detalleOrden);
        List<DetalleOrden> obtenerDetallesOrdenPorUsuario(Usuario usuario);
        DetalleOrden obtenerDetalleOrdenPorUsuarioYProducto(Usuario usuario, Producto producto);
    	
    	
    	
    	
}
