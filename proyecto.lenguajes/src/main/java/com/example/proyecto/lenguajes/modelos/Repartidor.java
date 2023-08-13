package com.example.proyecto.lenguajes.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repartidor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repartidor {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	private String nombre;
    private String edad;
    private String telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "repartidor")
    private List<Orden> ordenes;


}
