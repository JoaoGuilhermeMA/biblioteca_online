package br.com.joaoguilherme.biblioteca_online.reserva;

import java.time.LocalDate;

public record ReservaResponse(
        Integer id,
        Integer usuarioId,
        Integer livroId,
        LocalDate dataReserva,
        String status) {
}
