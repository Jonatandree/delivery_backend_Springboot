package com.example.proyecto.lenguajes.modelos;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;
	private double subtotal;
	private double total;

	
    @ManyToOne
	private Usuario usuario;
	
    
	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detalle;
	

    @ManyToOne
	private Repartidor repartidor;
}
