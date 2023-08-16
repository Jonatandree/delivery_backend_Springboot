package com.example.proyecto.lenguajes.services.impl;
import org.springframework.stereotype.Service;
import com.example.proyecto.lenguajes.services.DireccionServices;

@Service
public class DireccionServicesImpl implements DireccionServices {

	
	
	
	
	@Override
	public double calcularCostoEnvio(double xUsuario, double yUsuario) {
		
		
		double xCasaMatriz = 14.071361229336302;
        double yCasaMatriz = -87.20639413855496;

        double distancia = Math.sqrt(Math.pow(xUsuario - xCasaMatriz, 2) + Math.pow(yUsuario - yCasaMatriz, 2));
        double distanciaEnKm = distancia*111;

        double costoEnvio;

        if (distanciaEnKm <= 2) {
            costoEnvio = 15.0;
        } else {
            costoEnvio = 15.0 + (5.0 * (distanciaEnKm - 2));
        }
        return costoEnvio;
		
		
	}
	
		
       

}
