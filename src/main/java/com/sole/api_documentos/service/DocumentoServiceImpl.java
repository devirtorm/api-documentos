package com.sole.api_documentos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sole.api_documentos.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    @Value("${delphi.service.url:http://192.168.1.241:3200/GeneraDocumento}")
    private String delphiServiceUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GenerarDocumentoResponse procesarDocumento(GenerarDocumentoRequest request) {
        try {
            DelphiDocumentoRequest delphiRequest = new DelphiDocumentoRequest();
            DelphiDocumentoRequest.Documento doc = new DelphiDocumentoRequest.Documento();
            doc.setTipoDocumento(request.getTipoDocumento());
            doc.setCliProv(request.getCliProv());
            doc.setAgente(request.getAgente());
            doc.setUsuario("SUPERVISOR"); 
            doc.setAlmacen(request.getAlmacen());
            doc.setMoneda(request.getClaveMoneda());
            
            doc.setDescuento1(request.getDescuento1() != null ? request.getDescuento1() : BigDecimal.ZERO);
            doc.setDescuento2(request.getDescuento2() != null ? request.getDescuento2() : BigDecimal.ZERO);
            doc.setDescuento3(request.getDescuento3() != null ? request.getDescuento3() : BigDecimal.ZERO);

            List<DelphiDocumentoRequest.Articulo> articulos = new ArrayList<>();
            for (DetalleRequest det : request.getDetalles()) {
                DelphiDocumentoRequest.Articulo art = new DelphiDocumentoRequest.Articulo();
                art.setArticulo(det.getArticulo());
                art.setDescripcion(det.getDescripcion() != null ? det.getDescripcion() : "");
                art.setUnidadMedida("PZA");
                art.setCantidad(det.getCantidad());
                art.setPrecio(det.getPrecio());
                
                art.setDescuento1(det.getDescuento1() != null ? det.getDescuento1() : BigDecimal.ZERO);
                art.setDescuento2(det.getDescuento2() != null ? det.getDescuento2() : BigDecimal.ZERO);
                art.setDescuento3(det.getDescuento3() != null ? det.getDescuento3() : BigDecimal.ZERO);
                
                articulos.add(art);
            }
            doc.setArticulos(articulos);
            delphiRequest.setDocumentos(Collections.singletonList(doc));

            String jsonRequest = objectMapper.writeValueAsString(delphiRequest);

            // POST con Form URL Encoded
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("DetalleDocumento", jsonRequest);

            HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(map, headers);
            
            RestTemplate restTemplate = new RestTemplate();
            DelphiDocumentoResponse delphiResponse = restTemplate.postForObject(delphiServiceUrl, formEntity, DelphiDocumentoResponse.class);

            if (delphiResponse == null || delphiResponse.getEstatus() != 201 || delphiResponse.getDocumentos() == null || delphiResponse.getDocumentos().isEmpty()) {
                throw new RuntimeException("Error en respuesta del servicio Delphi. Estatus: " + (delphiResponse != null ? delphiResponse.getEstatus() : "null"));
            }

            DelphiDocumentoResponse.Documento respDoc = delphiResponse.getDocumentos().get(0);

            GenerarDocumentoResponse.Totales totales = GenerarDocumentoResponse.Totales.builder()
                    .subtotalGravado(respDoc.getSubtotal()) 
                    .subtotalExento(BigDecimal.ZERO)
                    .descuento(respDoc.getDescuento())
                    .iva(respDoc.getIva())
                    .totalFinal(respDoc.getTotal())
                    .build();

            GenerarDocumentoResponse.DatosDocumento datos = GenerarDocumentoResponse.DatosDocumento.builder()
                    .folioGenerado(respDoc.getFolio())
                    .tipoDocumento(request.getTipoDocumento())
                    .fechaGeneracion(LocalDateTime.now())
                    .totales(totales)
                    .build();

            return GenerarDocumentoResponse.builder()
                    .success(true)
                    .mensaje("Documento generado exitosamente vía Delphi.")
                    .datos(datos)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con el servicio Delphi: " + e.getMessage(), e);
        }
    }
}
