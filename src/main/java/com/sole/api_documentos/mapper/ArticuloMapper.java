package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.ArticuloSummaryDTO;
import com.sole.api_documentos.entity.Articulo;
import com.sole.api_documentos.service.ArticuloService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloMapper {
    Articulo toEntity(ArticuloSummaryDTO articuloDTO);
    ArticuloSummaryDTO toDTO(Articulo articulo);
    List<ArticuloSummaryDTO> toDTOList(List<Articulo> articulos);
}
