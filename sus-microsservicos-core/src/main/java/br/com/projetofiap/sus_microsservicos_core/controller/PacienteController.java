package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarPacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.PacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final Logger logger = LoggerFactory.getLogger(PacienteController.class);
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Page<BuscarPacienteDTO>> buscarTodosPacientes(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        this.logger.info("GET -> /api/v1/pacientes?page={}&size={}", page, size);
        Page<BuscarPacienteDTO> pacientesDTO = this.pacienteService.buscarTodosPacientes(page, size);
        return new ResponseEntity(pacientesDTO.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarPacienteDTO> buscarPacientePorId(
            @PathVariable String id
    ) {
        this.logger.info("GET -> /api/v1/pacientes/{}", id);
        List<BuscarPacienteDTO> buscandoPaciente = this.pacienteService.buscarPacientePorId(id);
        return new ResponseEntity(buscandoPaciente, HttpStatus.OK);
    }

    @GetMapping("{id}/tem-contato")
    public ResponseEntity<Boolean> pacienteTemContato(@PathVariable String id) {
        this.logger.info("GET -> /api/v1/pacientes/{}/tem-contato", id);
        boolean temContato = pacienteService.verificarContato(id);
        return ResponseEntity.ok(temContato);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarPaciente(
            @Valid @RequestBody PacienteDTO dto
    ) {
        this.logger.info("POST -> /api/v1/pacientes");
        this.pacienteService.cadastrarPaciente(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarPaciente(
            @PathVariable("id") String id,
            @Valid @RequestBody PacienteDTO dto
    ) {
        this.logger.info("PUT -> /api/v1/pacientes/{}", id);
        this.pacienteService.atualizarPaciente(id, dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerPaciente(
            @PathVariable("id") String id
    ) {
        this.logger.info("DELETE -> /api/v1/pacientes/{}", id);
        this.pacienteService.removerPaciente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
