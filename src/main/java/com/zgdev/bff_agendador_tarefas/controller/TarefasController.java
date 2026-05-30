package com.zgdev.bff_agendador_tarefas.controller;

import com.zgdev.bff_agendador_tarefas.business.TarefasService;
import com.zgdev.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.zgdev.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.zgdev.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping("")
    @Operation(summary = "Cadastrar tarefa", description = "Realiza o cadastro de uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tarefa cadastrada com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefas(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por período",
            description = "Busca tarefas agendadas dentro de um período específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tarefas encontradas com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinal,

            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token)
        );
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas do usuário",
            description = "Busca todas as tarefas vinculadas ao usuário autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tarefas encontradas com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tarefa",
            description = "Remove uma tarefa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tarefa removida com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<Void> deletarTarefaPorId(@PathVariable String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletarTarefaPorID(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar status da notificação",
            description = "Atualiza o status da notificação de uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Status alterado com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<TarefasDTOResponse> alterarStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,

            @RequestParam("id") String id,

            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.alterarStatus(status, id, token)
        );
    }

    @PutMapping
    @Operation(summary = "Atualizar tarefa",
            description = "Atualiza os dados de uma tarefa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tarefa atualizada com sucesso"),

            @ApiResponse(responseCode = "401",
                    description = "Usuário não autorizado"),

            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de servidor")
    })
    public ResponseEntity<TarefasDTOResponse> alterarTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                                     @RequestParam("id") String id,
                                                     @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.updateTarefas(tarefasDTO, id, token)
        );
    }
}