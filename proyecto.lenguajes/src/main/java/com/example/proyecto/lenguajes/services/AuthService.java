package com.example.proyecto.lenguajes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.repositories.UsuarioRepository;


@Service
public class AuthService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    public Optional<Usuario> autenticarUsuario(String email, String password) {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

}
