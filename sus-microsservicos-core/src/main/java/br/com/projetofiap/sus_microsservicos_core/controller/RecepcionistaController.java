package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarRecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.RecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.service.RecepcionistaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recpcionistas")
@RequiredArgsConstructor
public class RecepcionistaController {

    private final Logger logger = LoggerFactory.getLogger(RecepcionistaController.class);
    private final RecepcionistaService recepcionistaService;

    @GetMapping
    public ResponseEntity<Page<BuscarRecepcionistaDTO>> buscarTodosRecepcionistas(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        this.logger.info("GET -> /api/v1/recepcionistas?page={}&size={}", page, size);
        Page<BuscarRecepcionistaDTO> recepcionistasDTO = this.recepcionistaService.buscarTodosRecepcionistas(page, size);
        return new ResponseEntity(recepcionistasDTO.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarRecepcionistaDTO> buscarRecepcionistasPorId(
            @PathVariable String id
    ) {
        this.logger.info("GET -> /api/v1/recepcionistas?id={}", id);
        List<BuscarRecepcionistaDTO> buscandoRecepcionista = this.recepcionistaService.buscarRecepcionistaPorId(id);
        return new ResponseEntity(buscandoRecepcionista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarRecepcionista(
            @RequestBody RecepcionistaDTO dto
    ){
        this.logger.info("POST -> /api/v1/recepcionistas");
        this.recepcionistaService.cadastrarRecepcionista(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarRecepcionista(
            @PathVariable("id") String id,
            @RequestBody RecepcionistaDTO dto
    ){
        this.logger.info("PUT -> /api/v1/recepcionistas");
        this.recepcionistaService.atualizarRecepcionista(id, dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerRecepcionista(
            @PathVariable("id") String id
    ){
        this.logger.info("DELETE -> /api/v1/recepcionistas/{id}", id);
        this.recepcionistaService.removerRecepcionista(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
