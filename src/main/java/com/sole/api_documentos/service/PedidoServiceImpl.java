package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.PedidoHistorialDTO;
import com.sole.api_documentos.entity.Pedido;
import com.sole.api_documentos.mapper.PedidoMapper;
import com.sole.api_documentos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    public Page<PedidoHistorialDTO> getHistorialByAgenteId(String id, String search, Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findByAgente(id, search, pageable);
        return pedidosPage.map(pedidoMapper::toResponseDto);
    }

    @Override
    public Page<PedidoHistorialDTO> getHistorialByClienteId(String id, Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findByCliProv(id,pageable);
        return pedidosPage.map(pedidoMapper::toResponseDto);
    }
}
