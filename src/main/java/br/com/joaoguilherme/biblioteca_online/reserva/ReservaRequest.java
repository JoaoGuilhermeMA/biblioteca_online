package br.com.joaoguilherme.biblioteca_online.reserva;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservaRequest(
        @NotNull Integer usuarioId,
        @NotNull Integer livroId,
        @NotNull LocalDate dataReserva) {
}
