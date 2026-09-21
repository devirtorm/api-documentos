package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.PrecioCliente;
import com.sole.api_documentos.entity.PrecioClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecioClienteRepository extends JpaRepository<PrecioCliente, PrecioClienteId> {
    List<PrecioCliente> findByIdCliente(String cliente);
    // Para modo offline: descargar todas las reglas
    List<PrecioCliente> findAll();

    // Para modo offline filtrado por agente
    @Query("SELECT p FROM PrecioCliente p, Cliente c WHERE p.id.cliente = c.clave AND c.agente = :agente")
    List<PrecioCliente> findByAgente(@org.springframework.data.repository.query.Param("agente") String agente);
}
