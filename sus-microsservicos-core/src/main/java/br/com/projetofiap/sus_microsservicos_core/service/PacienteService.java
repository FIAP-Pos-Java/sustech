package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarPacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.PacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.PacienteMapper;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioInexistenteException;
import br.com.projetofiap.sus_microsservicos_core.model.Paciente;
import br.com.projetofiap.sus_microsservicos_core.model.factory.UsuarioFactoryImpl;
import br.com.projetofiap.sus_microsservicos_core.repository.PacienteRepository;
import br.com.projetofiap.sus_microsservicos_core.validator.PacienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioFactoryImpl usuarioFactory;
    private final PacienteMapper pacienteMapper;
    private final PacienteValidator pacienteValidator;

    private static final String USUARIO_INEXISTENTE = "usuário não encontrado";

    public Page<BuscarPacienteDTO> buscarTodosPacientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Paciente> pacientes = this.pacienteRepository.findAll(pageable);
        Page<BuscarPacienteDTO> pacientesDTO = pacientes.map(pacienteMapper::toDTO);
        return pacientesDTO;
    }

    public List<BuscarPacienteDTO> buscarPacientePorId(String id) {
        UUID uuid = UUID.fromString(id);
        Paciente buscandoPaciente = this.pacienteRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        return List.of(this.pacienteMapper.toDTO(buscandoPaciente));
    }

    public void cadastrarPaciente(PacienteDTO dto) {
        Paciente novoPaciente = (Paciente) this.usuarioFactory.criandoUsuario(dto);
        this.pacienteValidator.validandoCPF(novoPaciente);
        this.pacienteRepository.save(novoPaciente);
    }

    public void atualizarPaciente(String id, PacienteDTO dto) {
        UUID uuid = UUID.fromString(id);
        Paciente buscandoPaciente = this.pacienteRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));

        Paciente atualizandoPaciente = (Paciente) this.usuarioFactory.criandoUsuario(dto);
        atualizandoPaciente.setId(buscandoPaciente.getId());
        this.pacienteRepository.save(atualizandoPaciente);
    }

    public void removerPaciente(String id) {
        UUID uuid = UUID.fromString(id);
        Paciente buscandoPaciente = this.pacienteRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        this.pacienteRepository.deleteById(uuid);
    }

    public boolean verificarContato(String id) {
        UUID uuid = UUID.fromString(id);
        Paciente paciente = this.pacienteRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        
        return (paciente.getEmail() != null && !paciente.getEmail().isEmpty()) || 
               (paciente.getTelefone() != null && !paciente.getTelefone().isEmpty());
    }
}
