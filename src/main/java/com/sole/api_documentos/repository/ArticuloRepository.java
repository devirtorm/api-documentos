package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.Articulo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String> {

    @Query("""
        SELECT a FROM Articulo a
        WHERE a.clasificacion.clasificacionId = 'IG'
        AND (
            :search IS NULL OR :search = ''
            OR LOWER(a.clave) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
        )
    """)
    Page<Articulo> findBySearch(@Param("search") String search, Pageable pageable);
}
