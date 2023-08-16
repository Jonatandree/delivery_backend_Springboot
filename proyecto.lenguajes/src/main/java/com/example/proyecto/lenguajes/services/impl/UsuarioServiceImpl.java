package com.example.proyecto.lenguajes.services.impl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.repositories.UsuarioRepository;
import com.example.proyecto.lenguajes.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    
    
    
	@Override
	public Usuario findByIdO(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	
	@Override
	public void delete(Integer id) {
		usuarioRepository.deleteById(id);
	}
    
}
