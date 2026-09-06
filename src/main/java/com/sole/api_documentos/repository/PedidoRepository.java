package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {
    @Query("""
        SELECT p FROM Pedido p
        WHERE p.agente = :clienteId
        AND (
            :search IS NULL or :search = ''
                OR LOWER(p.folio) LIKE LOWER(CONCAT('%',:search,'%'))
                OR LOWER(p.cliProv) LIKE LOWER(CONCAT('%',:search,'%'))
                OR LOWER(p.status) LIKE LOWER(CONCAT('%',:search,'%'))
            )
    """)
    Page<Pedido> findByAgente(String clienteId, String search, Pageable pageable);
    @EntityGraph(attributePaths = {"detalles"})
    Page<Pedido> findByCliProv(String clienteId, Pageable pageable);
}
