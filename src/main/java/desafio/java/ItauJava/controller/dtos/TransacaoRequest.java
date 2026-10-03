package desafio.java.ItauJava.controller.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequest(Double valor, OffsetDateTime dataHora) {
}
