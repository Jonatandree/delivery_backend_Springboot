package com.example.proyecto.lenguajes.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.services.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
    private ProductoServiceImpl productoserviceimpl;
	
	@GetMapping("/listar")
    public List<Producto> obtenerTodosLosProductos() {
        return productoserviceimpl.findAll();
    }
	
	@PostMapping("/guardar")
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoserviceimpl.save(producto);
    }
	
	
	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Producto> optionalProducto = productoserviceimpl.get(id);

        if (optionalProducto.isPresent()) {
            productoserviceimpl.delete(id);
            return ResponseEntity.ok("Producto eliminado con éxito.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/obtener/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id) {
	    Optional<Producto> optionalProducto = productoserviceimpl.get(id);

	    if (optionalProducto.isPresent()) {
	        Producto producto = optionalProducto.get();
	        return ResponseEntity.ok(producto);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
    @PutMapping("/editar")
	public Producto editarProducto(@RequestBody Producto producto) {
		return productoserviceimpl.save(producto);
	}
}






















