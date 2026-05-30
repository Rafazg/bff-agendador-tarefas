package com.zgdev.bff_agendador_tarefas.business;

import com.zgdev.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.zgdev.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.zgdev.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefas(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletarTarefaPorID(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alterarStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alterarStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.alterarTarefas(dto, id, token);
    }
}
