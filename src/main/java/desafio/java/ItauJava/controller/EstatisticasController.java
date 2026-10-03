package desafio.java.ItauJava.controller;


import desafio.java.ItauJava.controller.dtos.EstatisticasResponse;
import desafio.java.ItauJava.service.EstatisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatítisca de transações"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
    public ResponseEntity<EstatisticasResponse> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticas(intervaloBusca));


    }

}
