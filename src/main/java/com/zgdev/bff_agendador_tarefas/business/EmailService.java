package com.zgdev.bff_agendador_tarefas.business;

import com.zgdev.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.zgdev.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefasDTOResponse tarefaDto){
        emailClient.enviarEmail(tarefaDto);
    }
}
