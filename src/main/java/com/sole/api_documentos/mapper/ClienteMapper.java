package com.sole.api_documentos.mapper;

import com.sole.api_documentos.DTO.ClienteDetailDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.DTO.ClienteSummaryDTO;
import com.sole.api_documentos.entity.Cliente;
import com.sole.api_documentos.entity.ClientePublicoGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDetailDTO toDTO(Cliente cliente);
    List<ClienteSummaryDTO> toDTOList(List<Cliente> clientes);
    Cliente toEntity(ClienteSummaryDTO clienteDTO);
}
