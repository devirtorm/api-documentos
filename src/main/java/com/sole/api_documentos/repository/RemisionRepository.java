package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.Remision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RemisionRepository extends JpaRepository<Remision, String> {
    @Query("""
        SELECT r FROM Remision r
        WHERE r.agente = :clienteId
        AND (
            :search IS NULL OR :search = ''
            OR LOWER(r.folio) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(r.cliProv) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(r.status) LIKE LOWER(CONCAT('%', :search, '%'))
        )
    """)
    Page<Remision> findByAgente(
            @Param("clienteId") String clienteId,
            @Param("search") String search,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"detalles"})
    Page<Remision> findByCliProvAndStatusNot(String clienteId, String status, Pageable pageable);
}
