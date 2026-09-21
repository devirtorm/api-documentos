package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.PrecioClienteDTO;
import com.sole.api_documentos.entity.PrecioCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrecioClienteMapper {

    @Mapping(source = "id.cliente", target = "cliente")
    @Mapping(source = "id.articulo", target = "articulo")
    PrecioClienteDTO toDTO(PrecioCliente entity);

    List<PrecioClienteDTO> toDTOList(List<PrecioCliente> entities);
}
