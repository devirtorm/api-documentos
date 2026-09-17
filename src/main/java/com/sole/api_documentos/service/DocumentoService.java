package com.sole.api_documentos.service;

import com.sole.api_documentos.dto.GenerarDocumentoRequest;
import com.sole.api_documentos.dto.GenerarDocumentoResponse;

public interface DocumentoService {
    GenerarDocumentoResponse procesarDocumento(GenerarDocumentoRequest request);
}
