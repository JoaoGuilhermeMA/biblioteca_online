package br.com.joaoguilherme.biblioteca_online.multa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MultaResponse(
        Integer id,
        Integer usuarioId,
        BigDecimal valor,
        LocalDate dataGeracao,
        Boolean pago) {
}
