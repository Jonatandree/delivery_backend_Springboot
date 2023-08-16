package com.example.proyecto.lenguajes.services.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.lenguajes.modelos.MetodoPago;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.repositories.MetodoPagoRepository;
import com.example.proyecto.lenguajes.repositories.UsuarioRepository;
import com.example.proyecto.lenguajes.services.MetodoPagoService;

@Service
public class MetodoPagoServiceImpl  implements MetodoPagoService {

	
	@Autowired
    private MetodoPagoRepository pagoRepository;
	
	@Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	
	@Override
	public MetodoPago save(MetodoPago metodoPago) {
		return pagoRepository.save(metodoPago);
	}


	@Override
	public MetodoPago obtenerMetodoDeOrdenPagoPorId(Integer id) {
		return pagoRepository.findById(id).orElse(null);
	}
	

	@Override
	public Usuario asignar(Integer usuarioId, Integer metodopagoId) {
		Usuario usuario = usuarioServiceImpl.findByIdO(usuarioId);
		MetodoPago metodoPago = obtenerMetodoDeOrdenPagoPorId(metodopagoId);
		metodoPago.setUsuario(usuario);
		usuario.getPago().add(metodoPago);
		usuariorepository.save(usuario);
		return usuario;
	}
	
	
	@Override
	public MetodoPago editarMetodoPagoById(MetodoPago id) {
		return pagoRepository.save(id);
		
	}

	@Override
	public List<MetodoPago> ListarMetodoPago() {
		return pagoRepository.findAll();
	}
	
	
	

}
