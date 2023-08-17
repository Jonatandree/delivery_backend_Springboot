package com.example.proyecto.lenguajes.services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.lenguajes.modelos.Producto;

public interface ProductoService {
    
    public Producto save( Producto producto);
	public Optional<Producto> get(Integer id);
	public void update(Producto producto);
	public void delete(Integer id);
	public List<Producto> findAll();
	public Producto getO(Integer id);
}
