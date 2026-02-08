package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.config.RabbitMQConfig;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.CirurgiaDTO;
import br.com.projetofiap.sus_microsservicos_core.event.CirurgiaAgendadaEvent;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioInexistenteException;
import br.com.projetofiap.sus_microsservicos_core.model.enums.StatusCirurgia;
import br.com.projetofiap.sus_microsservicos_core.repository.MedicoRepository;
import br.com.projetofiap.sus_microsservicos_core.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CirurgiaOrquestradorService {
    
    private final Logger logger = LoggerFactory.getLogger(CirurgiaOrquestradorService.class);
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final RabbitTemplate rabbitTemplate;

    public void agendarCirurgia(CirurgiaDTO dto) {
        validarUsuarios(dto.pacienteId(), dto.medicoId());
        
        CirurgiaAgendadaEvent evento = new CirurgiaAgendadaEvent(
                dto.pacienteId(),
                dto.medicoId(),
                dto.dataCirurgia(),
                dto.horaCirurgia(),
                dto.local(),
                dto.descricao(),
                StatusCirurgia.AGENDADA
        );
        
        publicarEvento(evento);
        logger.info("Comando CREATE publicado para paciente {} com médico {}", dto.pacienteId(), dto.medicoId());
    }

    public void atualizarCirurgia(UUID cirurgiaId, CirurgiaDTO dto) {
        validarUsuarios(dto.pacienteId(), dto.medicoId());
        
        CirurgiaAgendadaEvent evento = new CirurgiaAgendadaEvent(
                dto.pacienteId(),
                dto.medicoId(),
                dto.dataCirurgia(),
                dto.horaCirurgia(),
                dto.local(),
                dto.descricao(),
                StatusCirurgia.AGENDADA
        );
        
        publicarEvento(evento);
        logger.info("Comando UPDATE publicado para cirurgia {}", cirurgiaId);
    }

    public void cancelarCirurgia(UUID cirurgiaId) {
        CirurgiaAgendadaEvent evento = new CirurgiaAgendadaEvent(
                null, null, null, null, null, null,
                StatusCirurgia.CANCELADA
        );
        
        publicarEvento(evento);
        logger.info("Comando DELETE publicado para cirurgia {}", cirurgiaId);
    }

    private void validarUsuarios(UUID pacienteId, UUID medicoId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new UsuarioInexistenteException("Paciente não encontrado"));
        medicoRepository.findById(medicoId)
                .orElseThrow(() -> new UsuarioInexistenteException("Médico não encontrado"));
    }
    
    private void publicarEvento(CirurgiaAgendadaEvent evento) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.CIRURGIA_AGENDADA_ROUTING_KEY,
                evento
        );
    }
}
