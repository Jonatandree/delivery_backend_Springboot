package com.example.proyecto.lenguajes.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyecto.lenguajes.modelos.Usuario;
import com.example.proyecto.lenguajes.services.AuthService;
import com.example.proyecto.lenguajes.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	
	@Autowired
    private UsuarioServiceImpl usuarioserviceimpl;
	
	
	@GetMapping("/listar")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioserviceimpl.findAll();
    }
	
	
	@PostMapping("/guardar")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioserviceimpl.save(usuario);
    }
	
	@GetMapping("obtener/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
		Optional<Usuario> optionalUsuario = usuarioserviceimpl.findById(id);

	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        return new ResponseEntity<>(usuario, HttpStatus.OK);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
    }
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
	    Optional<Usuario> optionalUsuarioExistente = usuarioserviceimpl.findById(id);

	    if (optionalUsuarioExistente.isPresent()) {
	        Usuario usuarioExistente = optionalUsuarioExistente.get();
	        usuarioExistente.setNombre(usuarioActualizado.getNombre());
	        usuarioExistente.setUsername(usuarioActualizado.getUsername());
	        usuarioExistente.setEmail(usuarioActualizado.getEmail());
	        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
	        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
	        usuarioExistente.setTipo(usuarioActualizado.getTipo());
	        usuarioExistente.setPassword(usuarioActualizado.getPassword());
	        usuarioExistente.setMetodopago(usuarioActualizado.getMetodopago());
	        usuarioserviceimpl.save(usuarioExistente); // Guardar las actualizaciones

	        return ResponseEntity.ok("Usuario actualizado exitosamente");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	

    @Autowired
    private AuthService authService;
    
    @PostMapping("/acceder")
    public ResponseEntity<String> acceder(@RequestBody Usuario usuario) {
        Optional<Usuario> user = authService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());

        if (user.isPresent()) {
            if (user.get().getTipo().equals("ADMIN")) {
                return ResponseEntity.ok("ADMIN");
            } else {
                return ResponseEntity.ok("USUARIO");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
    	usuarioserviceimpl.delete(id);
        return "Usuario eliminado con éxito";
    }
    
    
    

	
	
	
	
	
	
	
	
	
	
    
}
