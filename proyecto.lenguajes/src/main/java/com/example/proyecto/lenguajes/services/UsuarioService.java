package com.example.proyecto.lenguajes.services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.lenguajes.modelos.Usuario;

public interface UsuarioService {
	


    List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	Usuario save (Usuario usuario);
	Optional<Usuario> findByEmail(String email);
	public void delete(Integer id);
	Usuario findByIdO(Integer id);
	
	
	
}