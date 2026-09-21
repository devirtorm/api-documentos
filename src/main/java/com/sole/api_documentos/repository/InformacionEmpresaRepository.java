package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.InformacionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionEmpresaRepository extends JpaRepository<InformacionEmpresa, String> {
}
