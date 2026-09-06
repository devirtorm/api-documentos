package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.PedidoDetalleDTO;
import com.sole.api_documentos.DTO.PedidoHistorialDTO;
import com.sole.api_documentos.entity.Pedido;
import com.sole.api_documentos.entity.PedidoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoHistorialDTO toResponseDto(Pedido entity);

    @Mapping(source = "id.consecutivo", target = "consecutivo")
    PedidoDetalleDTO toPedidoDetalleDTO(PedidoDetalle entity);
}
