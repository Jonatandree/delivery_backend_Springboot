package com.example.proyecto.lenguajes.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direccion")
@Data
@NoArgsConstructor
public class Direccion {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    private Double x;
    private Double y;
    

    @ManyToOne
   	private Usuario usuario;
    
}
