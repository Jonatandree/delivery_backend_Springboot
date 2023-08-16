package com.example.proyecto.lenguajes.services;

import java.util.List;

import com.example.proyecto.lenguajes.modelos.MetodoPago;
import com.example.proyecto.lenguajes.modelos.Usuario;




public interface MetodoPagoService {
	
	MetodoPago save (MetodoPago metodoPago);
	
	MetodoPago obtenerMetodoDeOrdenPagoPorId(Integer id);
	
	Usuario asignar(Integer usuarioId, Integer metodopagoId);
	
	MetodoPago editarMetodoPagoById(MetodoPago id);
	
	List<MetodoPago> ListarMetodoPago();
	

}
