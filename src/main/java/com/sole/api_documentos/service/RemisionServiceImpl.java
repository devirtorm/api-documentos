package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.RemisionHistorialDTO;
import com.sole.api_documentos.entity.Remision;
import com.sole.api_documentos.mapper.RemisionMapper;
import com.sole.api_documentos.repository.RemisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemisionServiceImpl implements RemisionService{
    private final RemisionRepository remisionRepository;
    private final RemisionMapper remisionMapper;

    @Override
    public Page<RemisionHistorialDTO> getHistorialByAgenteId(String id, String search, Pageable pageable) {;
        Page<Remision> remisionesPage = remisionRepository.findByAgente(id,search,pageable);
        return remisionesPage.map(remisionMapper::toResponseDto);
    }

    @Override
    public Page<RemisionHistorialDTO> getHistorialByClienteId(String id,Pageable pageable) {
        String status = "CANCELADO";
        Page<Remision> remisionesPage = remisionRepository.findByCliProvAndStatusNot(id,status,pageable);

        return remisionesPage.map(remisionMapper::toResponseDto);
    }
}
