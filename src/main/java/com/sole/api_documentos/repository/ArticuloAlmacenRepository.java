package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.ArticuloAlmacen;
import com.sole.api_documentos.entity.ArticuloAlmacenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloAlmacenRepository extends JpaRepository<ArticuloAlmacen, ArticuloAlmacenId> {

}
