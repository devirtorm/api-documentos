package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.AgenteDTO;
import com.sole.api_documentos.entity.Agente;
import com.sole.api_documentos.exception.ResourceNotFoundException;
import com.sole.api_documentos.mapper.AgenteMapper;
import com.sole.api_documentos.repository.AgenteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenteServiceImpl implements AgenteService{
    private final AgenteRepository agenteRepository;
    private final AgenteMapper agenteMapper;

    @Override
    public AgenteDTO getById(String clave) {
        Agente agente = agenteRepository.findById(clave).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el agente con clave: " + clave)
        );
        return agenteMapper.toDTO(agente);
    }

    @Override
    public List<AgenteDTO> getAll() {
        List<Agente> agentes = agenteRepository.findAll();
        return agenteMapper.toDTOList(agentes);
    }

    @Override
    public void delete() {

    }
}
