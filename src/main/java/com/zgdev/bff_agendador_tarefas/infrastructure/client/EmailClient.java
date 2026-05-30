package com.zgdev.bff_agendador_tarefas.infrastructure.client;

import com.zgdev.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
