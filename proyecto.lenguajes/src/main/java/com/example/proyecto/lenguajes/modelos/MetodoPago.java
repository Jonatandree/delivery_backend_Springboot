package com.example.proyecto.lenguajes.modelos;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "metodopago")
@Data
@Setter
@NoArgsConstructor
public class MetodoPago {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String numeroTarjeta; 
    
    private String fechaVencimiento; 
    
    private String csv;
    
    
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonIgnore
    private Usuario usuario;
    
    
    
    
    

}
