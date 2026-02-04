package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ){
        this.logger.info("GET -> /api/v1/medicos?page={}&size={}", page, size);
        Page<BuscarMedicoDTO> medicosDTO = this.medicoService.buscarTodosMedicos(page, size);
        return new ResponseEntity(medicosDTO.getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarMedico(
            @RequestBody MedicoDTO dto
    ){
        this.logger.info("POST -> /api/v1/medicos");
        this.medicoService.cadastrarMedico(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
