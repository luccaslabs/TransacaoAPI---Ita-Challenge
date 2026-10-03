package desafio.java.ItauJava.controller.dtos;

public record EstatisticasResponse(Long count,
                                   Double sum,
                                   Double avg,
                                   Double min,
                                   Double max) {
}
