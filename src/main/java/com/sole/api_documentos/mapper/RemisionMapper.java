package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.ClienteSummaryDTO;
import com.sole.api_documentos.DTO.RemisionDetalleDTO;
import com.sole.api_documentos.DTO.RemisionHistorialDTO;
import com.sole.api_documentos.entity.Cliente;
import com.sole.api_documentos.entity.Remision;
import com.sole.api_documentos.entity.RemisionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RemisionMapper {
    RemisionHistorialDTO toResponseDto(Remision entity);

    @Mapping(source = "id.consecutivo", target = "consecutivo")
    RemisionDetalleDTO toRemisionDetalleDTO(RemisionDetalle entity);
}
