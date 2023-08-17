package com.example.proyecto.lenguajes.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyecto.lenguajes.modelos.DetalleOrden;
import com.example.proyecto.lenguajes.modelos.MetodoPago;
import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Producto;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.services.impl.CarritoServicesImpl;
import com.example.proyecto.lenguajes.services.impl.DetalleOrdenServiceImpl;
import com.example.proyecto.lenguajes.services.impl.DireccionServicesImpl;
import com.example.proyecto.lenguajes.services.impl.MetodoPagoServiceImpl;
import com.example.proyecto.lenguajes.services.impl.OrdenServiceImpl;
import com.example.proyecto.lenguajes.services.impl.ProductoServiceImpl;
import com.example.proyecto.lenguajes.services.impl.UsuarioServiceImpl;



@RestController
@RequestMapping("/carrito")
public class CarritoController {
	
	// para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	// datos de la orden
	Orden orden = new Orden();
	
	@Autowired
	private OrdenServiceImpl ordenserviceimpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private ProductoServiceImpl productoServiceimpl;
	
	@Autowired
	private DetalleOrdenServiceImpl detalleordenserviseimpl;
	
	
	
	@Autowired
	private CarritoServicesImpl carritoServiceImpl;
	
	@Autowired
	private DireccionServicesImpl direccionServicesImpl;
	
	@Autowired
    private DireccionServicesImpl envioService;
	
	
	@Autowired
	private MetodoPagoServiceImpl metodopagoserviceImpl;
	
	
	
	@PostMapping("/agregar")
    public ResponseEntity<Void> agregarProductoAlCarrito(@RequestParam int usuarioId, @RequestParam int productoId, @RequestParam int cantidad) {
        Usuario usuario = usuarioService.findByIdO(usuarioId);
        Producto producto = productoServiceimpl.getO(productoId);

        if (usuario != null && producto != null) {
            carritoServiceImpl.agregarProductoAlCarrito(usuario, producto, cantidad);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarProductoDelCarrito(@RequestParam int usuarioId, @RequestParam int productoId) {
        Usuario usuario = usuarioService.findByIdO(usuarioId);
        Producto producto = productoServiceimpl.getO(productoId);

        if (usuario != null && producto != null) {
        	carritoServiceImpl.eliminarProductoDelCarrito(usuario, producto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@GetMapping("/{usuarioId}")
    public List<DetalleOrden> obtenerCarrito(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.findByIdO(usuarioId);
        if (usuario != null) {
            return carritoServiceImpl.obtenerCarrito(usuario);
        } else {
            return new ArrayList<>();
        }
    }
	
	
	@PostMapping("/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@RequestParam int usuarioId) {
        Usuario usuario = usuarioService.findByIdO(usuarioId);
        if (usuario != null) {
        	carritoServiceImpl.vaciarCarrito(usuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@PostMapping("/generarorden")
	public ResponseEntity<Void> generarOrden(@RequestParam int usuarioId ,@RequestParam double xusuario,@RequestParam double yusuario,
			@RequestParam int metodopagoId) {
		metodopagoserviceImpl.asignar(usuarioId, metodopagoId);
	    Usuario usuario = usuarioService.findByIdO(usuarioId);
	    
	    
	    if (usuario != null) {
	        List<DetalleOrden> detallesOrden = carritoServiceImpl.obtenerCarrito(usuario);
	        if (!detallesOrden.isEmpty()) {
	            Orden orden = new Orden();
	            orden.setUsuario(usuario);
	            orden.setFechaCreacion(new Date());
	            orden.setNumero(ordenserviceimpl.generarNumeroOrden()); // Implementa esta función
	            double total = 0;
	            double subtotal = 0;
	            
	            ordenserviceimpl.save(orden);

	            for (DetalleOrden detalle : detallesOrden) {
	            	detalle.setOrden(orden);
	                detalleordenserviseimpl.guardarDetalleOrden(detalle);
	                subtotal += detalle.getTotal();
	                // Reducir el stock del producto
	                Producto producto = detalle.getProducto();
	                producto.setCantidad(producto.getCantidad() - detalle.getCantidad());
	                productoServiceimpl.save(producto);
	            }
	            
	            subtotal *= 1.15; // Aplicar impuesto del 15%
	            double costoEnvio = direccionServicesImpl.calcularCostoEnvio(xusuario, yusuario);
	            total = costoEnvio + subtotal;        
	            orden.setSubtotal(subtotal);
	            orden.setTotal(total);
	            ordenserviceimpl.save(orden); // Implementa esta función
	            carritoServiceImpl.vaciarCarrito(usuario);
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.badRequest().build(); // Carrito vacío
	        }
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	
	@GetMapping("/calcularCosto")
    public ResponseEntity<Double> calcularCostoEnvio(
            @RequestParam double xUsuario, @RequestParam double yUsuario) {
        double costoEnvio = envioService.calcularCostoEnvio(xUsuario, yUsuario);
        return ResponseEntity.ok(costoEnvio);
    }
	
	
	
	@PostMapping("/metodopago/guardar")
    public ResponseEntity<String> save(@RequestBody MetodoPago metodoPago) {
       // Verificar si la fecha de vencimiento de la tarjeta está vencida
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        try {
            Date fechaVencimientoDate = sdf.parse(metodoPago.getFechaVencimiento());
            Date fechaActual = new Date();

            if (fechaVencimientoDate.before(fechaActual)) {
                return ResponseEntity.badRequest().body("La tarjeta está vencida");
            }
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Formato de fecha de vencimiento inválido");
        }
        
        metodopagoserviceImpl.save(metodoPago);
        return ResponseEntity.ok("Método de pago guardado exitosamente");
    }
	
	
	
	
	
	
	@PutMapping("/metodopago/editar")
	public MetodoPago editarMetodoPago( @RequestBody MetodoPago id) {
		return metodopagoserviceImpl.save(id);
	}
	
	
	@GetMapping("/metodopago/listar")
	public List<MetodoPago> listarMetodoPago() {
		return metodopagoserviceImpl.ListarMetodoPago();
	}
	
	@GetMapping("metodopago/obtener/{id}")
	public MetodoPago obtenerById( @PathVariable int id) {
		return metodopagoserviceImpl.obtenerMetodoDeOrdenPagoPorId(id);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
    
    
    
    

}
