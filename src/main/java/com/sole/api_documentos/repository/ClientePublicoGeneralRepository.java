package com.sole.api_documentos.repository;

import com.sole.api_documentos.entity.ClientePublicoGeneral;
import com.sole.api_documentos.entity.ClientePublicoGeneralId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientePublicoGeneralRepository extends JpaRepository<ClientePublicoGeneral, ClientePublicoGeneralId> {
    List<ClientePublicoGeneral> findByIdAgente(String agente);
}
