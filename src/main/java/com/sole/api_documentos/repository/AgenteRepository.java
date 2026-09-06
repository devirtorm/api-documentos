package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, String> {
}
