package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.ArticuloAlmacen;
import com.sole.api_documentos.entity.ArticuloAlmacenId;
import com.sole.api_documentos.DTO.InventarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloAlmacenRepository extends JpaRepository<ArticuloAlmacen, ArticuloAlmacenId> {

    @Query(value = "SELECT " +
            "      ialm.Articulo as articulo, " +
            "      i.Descripcion as descripcion, " +
            "      ialm.Existencia as existencia, " +
            "      ISNULL(ve.Vendida, 0) + ISNULL(vd.Vendida, 0) as vendida " +
            "  FROM IArticuloAlmacen ialm " +
            "  INNER JOIN IArticulo i " +
            "      ON i.Clave = ialm.Articulo " +
            "  LEFT JOIN ( " +
            "      SELECT " +
            "          d.Articulo, " +
            "          SUM(d.Cantidad) AS Vendida " +
            "      FROM VRemisionD d " +
            "      INNER JOIN VRemisionE e ON e.Folio = d.Folio " +
            "      WHERE CAST(e.Fecha AS DATE) = CAST(GETDATE() AS DATE) AND e.Status != 'CANCELADO' " +
            "      GROUP BY d.Articulo " +
            "  ) ve " +
            "      ON ve.Articulo = i.Clave " +
            "  LEFT JOIN ( " +
            "      SELECT " +
            "          d.Articulo, " +
            "          SUM(d.Cantidad) AS Vendida " +
            "      FROM VPedidoD d " +
            "      INNER JOIN VPedidoE e ON e.Folio = d.Folio " +
            "      WHERE CAST(e.Fecha AS DATE) = CAST(GETDATE() AS DATE) AND e.Status != 'CANCELADO' " +
            "      GROUP BY d.Articulo " +
            "  ) vd " +
            "      ON vd.Articulo = i.Clave " +
            "  WHERE ialm.Almacen = :almacen " +
            "  AND (:search IS NULL OR LOWER(ialm.Articulo) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.Descripcion) LIKE LOWER(CONCAT('%', :search, '%')))",
            countQuery = "SELECT COUNT(*) FROM IArticuloAlmacen ialm " +
            "  INNER JOIN IArticulo i " +
            "      ON i.Clave = ialm.Articulo " +
            "  WHERE ialm.Almacen = :almacen " +
            "  AND (:search IS NULL OR LOWER(ialm.Articulo) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.Descripcion) LIKE LOWER(CONCAT('%', :search, '%')))",
            nativeQuery = true)
    Page<InventarioProjection> findInventarioByAlmacen(
            @Param("almacen") String almacen,
            @Param("search") String search,
            Pageable pageable);
}
