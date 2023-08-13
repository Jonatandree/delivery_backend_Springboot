package com.example.proyecto.lenguajes.controllers;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.proyecto.lenguajes.modelos.Orden;
import com.example.proyecto.lenguajes.modelos.Repartidor;
import com.example.proyecto.lenguajes.services.impl.RepartidorServiceImpl;


@RestController
@RequestMapping("/repartidores")
public class RepartidorController {
	
	
    @Autowired
    private RepartidorServiceImpl repartidorServiceImpl;

    @PostMapping("/crear")
    public ResponseEntity<Repartidor> crearRepartidor(@RequestBody Repartidor repartidor) {
        Repartidor nuevoRepartidor = repartidorServiceImpl.crearRepartidor(repartidor);
        return new ResponseEntity<>(nuevoRepartidor, HttpStatus.CREATED);
    }

     @GetMapping("obtener/{id}")
    public ResponseEntity<Repartidor> obtenerRepartidorPorId(@PathVariable Integer id) {
        Repartidor repartidor = repartidorServiceImpl.obtenerRepartidorPorId(id);
        return new ResponseEntity<>(repartidor, HttpStatus.OK);
    }
     

    @GetMapping
    public ResponseEntity<List<Repartidor>> obtenerTodosLosRepartidores() {
        List<Repartidor> repartidores = repartidorServiceImpl.obtenerTodosLosRepartidores();
        return new ResponseEntity<>(repartidores, HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarRepartidor(@PathVariable Integer id) {
        repartidorServiceImpl.eliminarRepartidor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/asignarpedido")
    public ResponseEntity<Repartidor> asignarPedido(@RequestParam Integer repartidorId, @RequestParam Integer ordenId) {
        try {
            Repartidor repartidor = repartidorServiceImpl.asignarOrdenes(repartidorId, ordenId);
            return new ResponseEntity<>(repartidor, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/historialordenes")
    public ResponseEntity<List<Orden>> obtenerHistorialOrdenes(@PathVariable Integer id) { // Cambio a Integer
        List<Orden> historialOrdenes = repartidorServiceImpl.findOrdenesById(id);
        return new ResponseEntity<>(historialOrdenes, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
