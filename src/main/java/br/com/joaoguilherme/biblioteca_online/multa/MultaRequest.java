package br.com.joaoguilherme.biblioteca_online.multa;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record MultaRequest(
        @NotNull Integer usuarioId,
        @NotNull BigDecimal valor,
        @NotNull LocalDate dataGeracao) {
}
