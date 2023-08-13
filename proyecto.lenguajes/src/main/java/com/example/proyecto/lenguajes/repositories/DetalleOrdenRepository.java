package com.example.proyecto.lenguajes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden,Integer> {
    
	List<DetalleOrden> findByOrden(Orden orden);
    DetalleOrden findByOrdenAndProducto(Orden orden, Producto producto);
    List<DetalleOrden> findByUsuario(Usuario usuario);
    DetalleOrden findByUsuarioAndProducto(Usuario usuario, Producto producto);
	
}
