package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.AgenteDTO;
import com.sole.api_documentos.entity.Agente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgenteMapper {
    Agente toEntity(AgenteDTO agenteDTO);
    AgenteDTO toDTO(Agente agente);
    List<AgenteDTO> toDTOList(List<Agente> agentes);
}
