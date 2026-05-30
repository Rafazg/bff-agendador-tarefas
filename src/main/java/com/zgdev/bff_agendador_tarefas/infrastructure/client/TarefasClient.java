package com.zgdev.bff_agendador_tarefas.infrastructure.client;

import com.zgdev.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.zgdev.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping("")
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                             @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String toekn);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping("/{id}")
    void deletarTarefaPorId(@PathVariable String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                        @RequestParam("id") String id,
                                        @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse alterarTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);
}
