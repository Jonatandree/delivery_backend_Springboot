package com.example.proyecto.lenguajes.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrden {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;

    @ManyToOne
	private Orden orden;
    
    @ManyToOne
   	private Usuario usuario;
	
	@ManyToOne
	private Producto producto;
}
