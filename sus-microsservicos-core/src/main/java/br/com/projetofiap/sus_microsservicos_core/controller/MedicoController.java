package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.service.MedicoService;
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
@RequestMapping("api/v1/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final Logger logger = LoggerFactory.getLogger(MedicoController.class);
    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<BuscarMedicoDTO>> buscarTodosMedicos(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        this.logger.info("GET -> /api/v1/medicos?page={}&size={}", page, size);
        Page<BuscarMedicoDTO> medicosDTO = this.medicoService.buscarTodosMedicos(page, size);
        return new ResponseEntity(medicosDTO.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarMedicoDTO> buscarMedicoPorId(
            @PathVariable String id
    ) {
        this.logger.info("GET -> /api/v1/medicos?id={}", id);
        List<BuscarMedicoDTO> buscandoMedico = this.medicoService.buscarMedicoPorId(id);
        return new ResponseEntity(buscandoMedico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarMedico(
            @Valid @RequestBody MedicoDTO dto
    ) {
        this.logger.info("POST -> /api/v1/medicos");
        this.medicoService.cadastrarMedico(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarMedico(
            @PathVariable("id") String id,
            @Valid @RequestBody MedicoDTO dto
    ) {
        this.logger.info("PUT -> /api/v1/medicos");
        this.medicoService.atualizarMedico(id, dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerMedico(
            @PathVariable("id") String id
    ) {
        this.logger.info("DELETE -> /api/v1/medicos/{id}", id);
        this.medicoService.removerMedico(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
