package desafio.java.ItauJava.service;

import desafio.java.ItauJava.controller.dtos.EstatisticasResponse;
import desafio.java.ItauJava.controller.dtos.TransacaoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponse calcularEstatisticas(Integer intervaloBuscar) {

        log.info("Iniciada busca de estatísticas de transações pelo período de tempo " + intervaloBuscar);
        List<TransacaoRequest> transacoes = transacaoService.buscarTransacoes(intervaloBuscar);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponse(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequest::valor).summaryStatistics();

        log.info("Estatísticas retornadas com sucesso");
        return new EstatisticasResponse(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }

}
