package com.example.proyecto.lenguajes.services.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.services.CarritoServices;
import com.example.proyecto.lenguajes.services.DetalleOrdenService;




@Service
public class CarritoServicesImpl implements CarritoServices {
	

    @Autowired
    private DetalleOrdenService detalleOrdenService;
    

	@Override
	public void agregarProductoAlCarrito(Usuario usuario, Producto producto, int cantidad) {
		
		DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        detalleOrden.setUsuario(usuario);
        detalleOrdenService.guardarDetalleOrden(detalleOrden);
		
	}

	@Override
	public void eliminarProductoDelCarrito(Usuario usuario, Producto producto) {
		DetalleOrden detalleOrden = detalleOrdenService.obtenerDetalleOrdenPorUsuarioYProducto(usuario, producto);
        if (detalleOrden != null) {
            detalleOrdenService.eliminarDetalleOrden(detalleOrden);
        }
	}
	

	@Override
	public List<DetalleOrden> obtenerCarrito(Usuario usuario) {
		return detalleOrdenService.obtenerDetallesOrdenPorUsuario(usuario);
	}
	
	

	@Override
	public void vaciarCarrito(Usuario usuario) {
		List<DetalleOrden> detallesOrden = detalleOrdenService.obtenerDetallesOrdenPorUsuario(usuario);
        detallesOrden.forEach(detalleOrdenService::eliminarDetalleOrden);
    } 
}
