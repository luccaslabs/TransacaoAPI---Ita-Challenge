package desafio.java.ItauJava.service;

import desafio.java.ItauJava.controller.dtos.TransacaoRequest;
import infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final List<TransacaoRequest> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequest dto){
        log.info("Inciado o processamento de gravar transações " + dto);


        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw  new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso");
    }

    public void deletarTransacoes(){
        log.info("Iniciado processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequest> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciada buscas de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();
    }
}
