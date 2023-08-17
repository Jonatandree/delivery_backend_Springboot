package com.example.proyecto.lenguajes.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class GuardarImagenService {
	
	
	private String imageFolderPath = "src/main/java/com/example/proyecto/lenguajes/imagenes";
	

    public String saveImage(MultipartFile file) throws IOException {
        String nombreImagen = UUID.randomUUID().toString() + ".jpg"; 
        Path imagePath = Paths.get(imageFolderPath, nombreImagen);
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return nombreImagen;
        
    }
    
    
    
	
	
	

}
