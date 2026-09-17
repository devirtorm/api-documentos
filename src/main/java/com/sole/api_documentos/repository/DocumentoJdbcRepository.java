package com.sole.api_documentos.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DocumentoJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public String obtenerSiguienteFolio(String nombreTabla, String serie, int digitos) {
        String sql = "SELECT ISNULL(MAX(CAST(SUBSTRING(Folio, LEN(?) + 1, LEN(Folio) - LEN(?)) AS INTEGER)), 0) " +
                     "FROM " + nombreTabla + " " +
                     "WHERE SUBSTRING(Folio, 1, LEN(?)) = ? " +
                     "AND ISNUMERIC(SUBSTRING(Folio, LEN(?) + 1, LEN(Folio) - LEN(?))) = 1 " +
                     "AND LEN(SUBSTRING(Folio, LEN(?) + 1, LEN(Folio) - LEN(?))) = ?";

        Integer maxFolioActual = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                serie, serie, serie, serie, serie, serie, serie, serie, digitos
        );

        int nuevoFolioNumerico = (maxFolioActual != null ? maxFolioActual : 0) + 1;
        String formatoRelleno = "%0" + digitos + "d";
        return serie + String.format(formatoRelleno, nuevoFolioNumerico);
    }
}
