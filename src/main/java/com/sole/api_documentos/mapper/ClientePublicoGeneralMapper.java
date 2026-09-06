package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.entity.Cliente;
import com.sole.api_documentos.entity.ClientePublicoGeneral;
import com.sole.api_documentos.entity.ClientePublicoGeneralId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientePublicoGeneralMapper {
    // 1. Mapeo para el Cliente Base (VCliente)
    @Mapping(target = "esClienteBase", constant = "true")
    @Mapping(target = "diaRevision", ignore = true) // VCliente no tiene diaRevision
    ClientePublicoGeneralSummaryDTO toDtoFromBase(Cliente cliente);

    // Tu método actual para clientes de público general
    @Mapping(target = "clave", source = "general.clave")
    @Mapping(target = "nombre", source = "general.nombre")
    @Mapping(target = "telefono", source = "general.telefono")
    @Mapping(target = "ciudad", source = "general.ciudad")
    @Mapping(target = "agente", source = "general.id.agente")
    @Mapping(target = "esClienteBase", constant = "false")
    ClientePublicoGeneralSummaryDTO fusionarCliente(Cliente base, ClientePublicoGeneral general);

    // NUEVO: Método para clientes normales
    @Mapping(target = "esClienteBase", constant = "true")
    // MapStruct mapeará automáticamente campos con el mismo nombre (clave, nombre, telefono, ciudad, agente)
    ClientePublicoGeneralSummaryDTO clienteToSummaryDTO(Cliente cliente);

    @Mapping(target = "agente", source = "cliente.id.agente")
    ClientePublicoGeneralDTO toDto(ClientePublicoGeneral cliente);

    ClientePublicoGeneral toEntity(ClientePublicoGeneralDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ClientePublicoGeneralDTO dto, @MappingTarget ClientePublicoGeneral entity);
}
