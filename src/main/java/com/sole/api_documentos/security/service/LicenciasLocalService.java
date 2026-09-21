package com.sole.api_documentos.security.service;

import com.sole.api_documentos.security.dto.LicenciaPayload;
import com.sole.api_documentos.security.dto.RegistroDispositivoRequest;
import com.sole.api_documentos.entity.InformacionEmpresa;
import com.sole.api_documentos.repository.InformacionEmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenciasLocalService {

    private final InformacionEmpresaRepository empresaRepository;
    private final RestTemplate restTemplate = new RestTemplate(); 

    // TODO: Mover a properties
    private final String LICENCIAS_SERVER_URL = "http://localhost:3000/api/licencias/validar";

    public ResponseEntity<String> procesarRegistroDispositivo(RegistroDispositivoRequest request) {
        List<InformacionEmpresa> empresas = empresaRepository.findAll();
        
        if (empresas.isEmpty()) {
            throw new RuntimeException("No se encontró información de la empresa en la base de datos local (FInformacionEmpresa).");
        }
        
        InformacionEmpresa empresaLocal = empresas.get(0);

        LicenciaPayload payload = new LicenciaPayload();
        payload.setClaveIngresada(request.getClave());
        payload.setIdDispositivo(request.getIdDispositivo());
        payload.setSerialServidor("OMITIDO"); 
        payload.setNombreDispositivo(request.getNombre());
        payload.setEmpresa(empresaLocal.getNombreEmpresa());
        payload.setRfcEmpresa(empresaLocal.getRfc());

        return restTemplate.postForEntity(LICENCIAS_SERVER_URL, payload, String.class);
    }
    public boolean verificarAcceso(String licenseToken) {
        if (licenseToken == null || licenseToken.isEmpty()) {
            return false;
        }
        try {
            java.util.Map<String, String> body = new java.util.HashMap<>();
            body.put("token", licenseToken);
            body.put("serialServidor", "OMITIDO");

            String url = "http://localhost:3000/api/licencias/verificar-acceso";
            ResponseEntity<String> response = restTemplate.postForEntity(url, body, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Licencia inválida o revocada: " + e.getMessage());
            return false;
        }
    }
}
