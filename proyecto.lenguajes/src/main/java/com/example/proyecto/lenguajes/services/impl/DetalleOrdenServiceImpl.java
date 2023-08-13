package com.example.proyecto.lenguajes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.repositories.DetalleOrdenRepository;
import com.example.proyecto.lenguajes.repositories.OrdenRepository;
import com.example.proyecto.lenguajes.services.DetalleOrdenService;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
	
	
	private List<DetalleOrden> detalles = new ArrayList<>();
	
    private Orden orden = new Orden();
    
    @Autowired
    private OrdenRepository ordenrepository;

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

	@Override
	public Optional<DetalleOrden> get(Integer id) {
		return detalleOrdenRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(DetalleOrden detalleOrden) {
		detalleOrdenRepository.delete(detalleOrden);
	}

	@Override
	public void eliminarProductoCarrito(List<DetalleOrden> ordenesNueva) {
		detalleOrdenRepository.deleteAll();
		
	}
	
	
	
    // Resto del código
	@Override
    public void eliminarProductoDelCarrito(Integer idProducto) {
        DetalleOrden detalleOrden = detalleOrdenRepository.findById(idProducto).orElseThrow(() -> new NoSuchElementException("Producto no encontrado en el carrito"));
        detalles.remove(detalleOrden);
        double sumaTotal = detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(sumaTotal);
        ordenrepository.save(orden);
        detalleOrdenRepository.deleteById(idProducto);
    }
	
	
	
	
	
	
	
	

	@Override
	public void guardarDetalleOrden(DetalleOrden detalleOrden) {
		// TODO Auto-generated method stub
		detalleOrdenRepository.save(detalleOrden);
		
	}

	@Override
	public void eliminarDetalleOrden(DetalleOrden detalleOrden) {
		// TODO Auto-generated method stub
		detalleOrdenRepository.delete(detalleOrden);
		
	}

	@Override
	public List<DetalleOrden> obtenerDetallesOrdenPorUsuario(Usuario usuario) {
		return detalleOrdenRepository.findByUsuario(usuario);
	}

	@Override
	public DetalleOrden obtenerDetalleOrdenPorUsuarioYProducto(Usuario usuario, Producto producto) {
		return detalleOrdenRepository.findByUsuarioAndProducto(usuario, producto);
	}
    
}















